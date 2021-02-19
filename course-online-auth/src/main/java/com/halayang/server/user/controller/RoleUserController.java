package com.halayang.server.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.user.dto.RoleUserDTO;
import com.halayang.server.user.po.RoleUserPO;
import com.halayang.server.user.service.RoleUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-19 14:38:32
 */
@RestController
@RequestMapping("/roleUser")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    /**
     * 获取用户角色对象信息
     *
     * @param id 用户角色id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.roleUser.po.RoleUserPO>
     * @author YangYudi
     * @date 2021-02-19 14:38:32
     */
    @GetMapping("/{id}")
    public ResponseObject<RoleUserDTO> getOne(@PathVariable String id) {
        RoleUserPO po = roleUserService.getById(id);
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        BeanUtils.copyProperties(po, roleUserDTO);
        return ResponseResult.success(roleUserDTO);
    }

    /**
     * 用户角色分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.roleUser.po.RoleUserPO>>
     * @author YangYudi
     * @date 2021-02-19 14:38:32
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<RoleUserDTO>> roleUserList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<RoleUserPO> pageInfo = new PageInfo<>(roleUserService.list(new LambdaQueryWrapper<RoleUserPO>().orderByDesc(RoleUserPO::getId)));
        List<RoleUserPO> list = pageInfo.getList();
        List<RoleUserDTO> dtoList = CopyUtils.copyList(list, RoleUserDTO.class);
        PageDTO<RoleUserDTO> page = new PageDTO<RoleUserDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 用户角色添加或更新
     *
     * @param roleUserDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 14:38:32
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated RoleUserDTO roleUserDTO) {
        RoleUserPO roleUserPo = new RoleUserPO();
        BeanUtils.copyProperties(roleUserDTO, roleUserPo);
        boolean option = roleUserService.saveOrUpdate(roleUserPo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除用户角色
     *
     * @param id 用户角色id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 14:38:32
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = roleUserService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
