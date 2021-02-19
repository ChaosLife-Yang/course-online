package com.halayang.server.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.user.dto.RoleUserDTO;
import com.halayang.server.user.po.RoleUserPO;
import com.halayang.server.user.service.RoleUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @ApiOperation(value = "通过用户id获取角色id列表", httpMethod = "GET", notes = "通过用户id获取角色id列表")
    @GetMapping("/{id}")
    public ResponseObject<List<String>> getOne(@PathVariable String id) {
        List<String> collect = roleUserService.list(new LambdaQueryWrapper<RoleUserPO>()
                .eq(RoleUserPO::getUserId, id))
                .stream()
                .map(RoleUserPO::getRoleId)
                .collect(Collectors.toList());
        return ResponseResult.success(collect);
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
        boolean option = roleUserService.saveOrUpdate(roleUserDTO);
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
