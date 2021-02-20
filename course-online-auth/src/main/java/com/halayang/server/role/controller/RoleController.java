package com.halayang.server.role.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.role.dto.RoleDTO;
import com.halayang.server.role.po.RolePO;
import com.halayang.server.role.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-19 13:05:36
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色管理对象信息
     *
     * @param id 角色管理id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.role.po.RolePO>
     * @author YangYudi
     * @date 2021-02-19 13:05:36
     */
    @GetMapping("/{id}")
    public ResponseObject<RoleDTO> getOne(@PathVariable String id) {
        RolePO po = roleService.getById(id);
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(po, roleDTO);
        return ResponseResult.success(roleDTO);
    }

    /**
     * 角色管理分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.role.po.RolePO>>
     * @author YangYudi
     * @date 2021-02-19 13:05:36
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<RoleDTO>> roleList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<RolePO> pageInfo = new PageInfo<>(roleService.list(new LambdaQueryWrapper<RolePO>().orderByDesc(RolePO::getId)));
        List<RolePO> list = pageInfo.getList();
        List<RoleDTO> dtoList = CopyUtils.copyList(list, RoleDTO.class);
        PageDTO<RoleDTO> page = new PageDTO<RoleDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    @GetMapping("/all")
    public ResponseObject<List<RoleDTO>> allRole(){
        List<RolePO> list = roleService.list();
        List<RoleDTO> dtoList = CopyUtils.copyList(list, RoleDTO.class);
        return ResponseResult.success(dtoList);
    }

    /**
     * 角色管理添加或更新
     *
     * @param roleDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 13:05:36
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated RoleDTO roleDTO) {
        RolePO rolePo = new RolePO();
        BeanUtils.copyProperties(roleDTO, rolePo);
        boolean option = roleService.saveOrUpdate(rolePo);
        if (option) {
            return ResponseResult.success(rolePo.getId());
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除角色管理
     *
     * @param id 角色管理id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-02-19 13:05:36
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = roleService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
