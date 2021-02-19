package com.halayang.server.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.server.user.dto.RoleUserDTO;
import com.halayang.server.user.po.RoleUserPO;
import com.halayang.server.user.mapper.RoleUserMapper;
import com.halayang.server.user.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色用户关联 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUserPO> implements RoleUserService {

    @Override
    public boolean saveOrUpdate(RoleUserDTO roleUserDTO) {
        int count = this.count(new LambdaQueryWrapper<RoleUserPO>().eq(RoleUserPO::getUserId, roleUserDTO.getUserId()));
        if (count > 0) {
            //更新 先删再加
            return this.remove(new LambdaQueryWrapper<RoleUserPO>().eq(RoleUserPO::getUserId, roleUserDTO.getUserId()))
                    && addRoleUser(roleUserDTO);
        }
        //添加
        return addRoleUser(roleUserDTO);
    }

    private boolean addRoleUser(RoleUserDTO roleUserDTO) {
        if (!CollectionUtils.isEmpty(roleUserDTO.getRoleId())) {
            List<RoleUserPO> collect = roleUserDTO.getRoleId().stream()
                    .map(roleId -> new RoleUserPO()
                            .setRoleId(roleId)
                            .setUserId(roleUserDTO.getUserId()))
                    .collect(Collectors.toList());
            return this.saveBatch(collect);
        }
        return true;
    }
}
