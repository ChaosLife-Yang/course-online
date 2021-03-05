package com.halayang.server.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.config.oauth.RbacService;
import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.halayang.server.resource.service.ResourceService;
import com.halayang.server.role.po.RoleResourcePO;
import com.halayang.server.role.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资源管理 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16 13:48:23
 */
@Api(tags = {"资源管理"})
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 获取资源管理对象信息
     *
     * @param id 资源管理id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.resource.po.ResourcePO>
     * @author YangYudi
     * @date 2021-02-16 13:48:23
     */
    @ApiOperation(value = "获取课资源对象信息", httpMethod = "GET", notes = "获取课资源对象信息")
    @GetMapping("/{id}")
    public ResponseObject<ResourceDTO> getOne(@PathVariable String id) {
        ResourcePO po = resourceService.getById(id);
        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(po, resourceDTO);
        return ResponseResult.success(resourceDTO);
    }

    /**
     * 资源查询
     *
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.resource.po.ResourcePO>>
     * @author YangYudi
     * @date 2021-02-16 13:48:23
     */
    @ApiOperation(value = "资源查询", httpMethod = "GET", notes = "资源查询")
    @GetMapping("/list")
    public ResponseObject<List<ResourceDTO>> resourceList() {
        return ResponseResult.success(resourceService.resourceList());
    }

    /**
     * 资源管理添加或更新
     *
     * @param resourceDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-16 13:48:23
     */
    @ApiOperation(value = "资源管理添加或更新", httpMethod = "POST", notes = "资源管理添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated ResourceDTO resourceDTO) {
        ResourcePO resourcePo = new ResourcePO();
        BeanUtils.copyProperties(resourceDTO, resourcePo);
        boolean option = resourceService.saveOrUpdate(resourcePo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除资源管理
     *
     * @param id 资源管理id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-16 13:48:23
     */
    @ApiOperation(value = "删除资源管理", httpMethod = "GET", notes = "删除资源管理")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        roleResourceService.remove(new LambdaQueryWrapper<RoleResourcePO>().eq(RoleResourcePO::getResourceId, id));
        boolean option = resourceService.deleteResources(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "用户拥有的资源", httpMethod = "GET", notes = "用户拥有的资源")
    @GetMapping("/userResources/{id}")
    public ResponseObject<List<String>> getUserResources(@PathVariable String id) {
        List<ResourceAuthorityDTO> permission = resourceService.getPermissionByUserId(id);
        List<String> collect = RbacService.getPermissionUrls(permission);
        return ResponseResult.success(collect);
    }

}
