package com.halayang.server.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageQueryDTO;
import com.halayang.common.vo.PageVO;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.user.dto.UserDTO;
import com.halayang.server.user.po.RoleUserPO;
import com.halayang.server.user.po.UserPO;
import com.halayang.server.user.service.RoleUserService;
import com.halayang.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-17 12:18:37
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleUserService roleUserService;

    /**
     * 获取用户管理对象信息
     *
     * @param id 用户管理id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.user.po.UserPO>
     * @author YangYudi
     * @date 2021-01-17 12:18:37
     */
    @ApiOperation(value = "获取用户管理对象信息", httpMethod = "GET", notes = "获取用户管理对象信息")
    @GetMapping("/{id}")
    public ResponseObject<UserDTO> getOne(@PathVariable String id) {
        UserPO po = userService.getById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(po, userDTO);
        return ResponseResult.success(userDTO);
    }

    /**
     * 用户管理分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.user.po.UserPO>>
     * @author YangYudi
     * @date 2021-01-17 12:18:37
     */
    @ApiOperation(value = "用户管理分页查询", httpMethod = "POST", notes = "用户管理分页查询")
    @PostMapping("/list")
    public ResponseObject<PageVO<UserDTO>> userList(@RequestBody @Validated PageQueryDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<UserPO> pageInfo = new PageInfo<>(userService.list(new LambdaQueryWrapper<UserPO>().orderByDesc(UserPO::getId)));
        List<UserPO> list = pageInfo.getList();
        List<UserDTO> dtoList = CopyUtils.copyList(list, UserDTO.class);
        PageVO<UserDTO> page = new PageVO<UserDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 用户管理添加或更新
     *
     * @param userDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-17 12:18:37
     */
    @ApiOperation(value = "用户管理添加或更新", httpMethod = "POST", notes = "用户管理添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated UserDTO userDTO) {
        UserPO userPo = new UserPO();
        BeanUtils.copyProperties(userDTO, userPo);
        boolean option = userService.saveOrUpdateUser(userPo);
        if (option) {
            return ResponseResult.success(userPo.getId());
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除用户管理
     *
     * @param id 用户管理id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-17 12:18:37
     */
    @ApiOperation(value = "删除用户管理", httpMethod = "GET", notes = "删除用户管理")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        roleUserService.remove(new LambdaQueryWrapper<RoleUserPO>().eq(RoleUserPO::getUserId, id));
        boolean option = userService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "重置密码", httpMethod = "GET", notes = "根据用户id重置用户密码")
    @GetMapping("/reset/{id}")
    public ResponseObject<String> resetPassword(@PathVariable String id) {
        boolean option = userService.update(new LambdaUpdateWrapper<UserPO>()
                .set(UserPO::getPassword, BCryptUtils.encode("123456"))
                .eq(UserPO::getId, id)
        );
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
