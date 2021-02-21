package com.halayang.server.role.service.impl;

import com.halayang.server.role.po.RolePO;
import com.halayang.server.role.mapper.RoleMapper;
import com.halayang.server.role.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> getRoleByUserId(String userId) {
        return roleMapper.getRoleByUserId(userId);
    }
}
