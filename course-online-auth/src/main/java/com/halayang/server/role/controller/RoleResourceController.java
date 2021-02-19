package com.halayang.server.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.role.dto.RoleResourceDTO;
import com.halayang.server.role.po.RoleResourcePO;
import com.halayang.server.role.service.RoleResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/{id}")
    public ResponseObject<RoleResourceDTO> getOne(@PathVariable String id) {
        RoleResourcePO po = roleResourceService.getById(id);
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        BeanUtils.copyProperties(po, roleResourceDTO);
        return ResponseResult.success(roleResourceDTO);
    }

    /**
     * 角色资源分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.roleResource.po.RoleResourcePO>>
     * @author YangYudi
     * @date 2021-02-19 14:36:42
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<RoleResourceDTO>> roleResourceList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<RoleResourcePO> pageInfo = new PageInfo<>(roleResourceService.list(new LambdaQueryWrapper<RoleResourcePO>().orderByDesc(RoleResourcePO::getId)));
        List<RoleResourcePO> list = pageInfo.getList();
        List<RoleResourceDTO> dtoList = CopyUtils.copyList(list, RoleResourceDTO.class);
        PageDTO<RoleResourceDTO> page = new PageDTO<RoleResourceDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
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
        RoleResourcePO roleResourcePo = new RoleResourcePO();
        BeanUtils.copyProperties(roleResourceDTO, roleResourcePo);
        boolean option = roleResourceService.saveOrUpdate(roleResourcePo);
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
