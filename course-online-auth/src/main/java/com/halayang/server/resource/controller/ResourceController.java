package com.halayang.server.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.halayang.server.resource.service.ResourceService;
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
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

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
     * 资源管理分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.resource.po.ResourcePO>>
     * @author YangYudi
     * @date 2021-02-16 13:48:23
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<ResourceDTO>> resourceList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<ResourcePO> pageInfo = new PageInfo<>(resourceService.list(new LambdaQueryWrapper<ResourcePO>().orderByDesc(ResourcePO::getId)));
        List<ResourcePO> list = pageInfo.getList();
        List<ResourceDTO> dtoList = CopyUtils.copyList(list, ResourceDTO.class);
        PageDTO<ResourceDTO> page = new PageDTO<ResourceDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
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
        boolean option = resourceService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
