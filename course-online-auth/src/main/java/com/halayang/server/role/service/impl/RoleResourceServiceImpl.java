package com.halayang.server.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.server.role.dto.RoleResourceDTO;
import com.halayang.server.role.po.RoleResourcePO;
import com.halayang.server.role.mapper.RoleResourceMapper;
import com.halayang.server.role.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.user.dto.RoleUserDTO;
import com.halayang.server.user.po.RoleUserPO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源关联 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourcePO> implements RoleResourceService {

    @Override
    public boolean saveOrUpdate(RoleResourceDTO roleResourceDTO) {
        int count = this.count(new LambdaQueryWrapper<RoleResourcePO>().eq(RoleResourcePO::getRoleId, roleResourceDTO.getRoleId()));
        if (count > 0) {
            //更新 先删再加
            return this.remove(new LambdaQueryWrapper<RoleResourcePO>().eq(RoleResourcePO::getRoleId, roleResourceDTO.getRoleId()))
                    && addRoleResource(roleResourceDTO);
        }
        //添加
        return addRoleResource(roleResourceDTO);
    }

    private boolean addRoleResource(RoleResourceDTO roleResourceDTO) {
        if (!CollectionUtils.isEmpty(roleResourceDTO.getResourceId())) {
            List<RoleResourcePO> collect = roleResourceDTO.getResourceId().stream()
                    .map(resourceId -> new RoleResourcePO()
                            .setRoleId(roleResourceDTO.getRoleId())
                            .setResourceId(resourceId))
                    .collect(Collectors.toList());
            return this.saveBatch(collect);
        }
        return true;
    }
}
