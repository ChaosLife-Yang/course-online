package com.halayang.server.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.role.dto.RoleResourceDTO;
import com.halayang.server.role.po.RoleResourcePO;
import com.halayang.server.role.service.RoleResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-19 14:36:42
 */
@RestController
@RequestMapping("/roleResource")
public class RoleResourceController {

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 获取角色资源对象信息
     *
     * @param id 角色资源id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.roleResource.po.RoleResourcePO>
     * @author YangYudi
     * @date 2021-02-19 14:36:42
     */
    @ApiOperation(value = "通过角色id获取资源id列表", httpMethod = "GET", notes = "通过角色id获取资源id列表")
    @GetMapping("/{id}")
    public ResponseObject<List<String>> getOne(@PathVariable String id) {
        List<String> collect = roleResourceService.list(new LambdaQueryWrapper<RoleResourcePO>()
                .eq(RoleResourcePO::getRoleId, id))
                .stream()
                .map(RoleResourcePO::getResourceId)
                .collect(Collectors.toList());
        return ResponseResult.success(collect);
    }

    /**
     * 角色资源添加或更新
     *
     * @param roleResourceDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 14:36:42
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated RoleResourceDTO roleResourceDTO) {
        boolean option = roleResourceService.saveOrUpdate(roleResourceDTO);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除角色资源
     *
     * @param id 角色资源id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 14:36:42
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = roleResourceService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
