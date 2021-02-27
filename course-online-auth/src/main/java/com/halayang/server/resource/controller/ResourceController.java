package com.halayang.server.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.JacksonUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.config.oauth.RbacService;
import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.halayang.server.resource.service.ResourceService;
import com.halayang.server.role.po.RoleResourcePO;
import com.halayang.server.role.service.RoleResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源管理 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16 13:48:23
 */
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

    @GetMapping("/userResources/{id}")
    public ResponseObject<List<String>> getUserResources(@PathVariable String id) {
        List<ResourceAuthorityDTO> permission = resourceService.getPermissionByUserId(id);
        List<String> collect = RbacService.getPermissionUrls(permission);
        return ResponseResult.success(collect);
    }

}
