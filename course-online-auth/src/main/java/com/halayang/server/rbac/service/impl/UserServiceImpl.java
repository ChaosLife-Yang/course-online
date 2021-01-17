package com.halayang.server.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.rbac.mapper.UserMapper;
import com.halayang.server.rbac.po.UserPO;
import com.halayang.server.rbac.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {

}
