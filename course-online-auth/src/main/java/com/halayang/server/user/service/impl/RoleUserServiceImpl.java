package com.halayang.server.user.service.impl;

import com.halayang.server.user.po.RoleUserPO;
import com.halayang.server.user.mapper.RoleUserMapper;
import com.halayang.server.user.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
