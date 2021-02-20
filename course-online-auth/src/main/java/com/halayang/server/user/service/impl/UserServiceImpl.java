package com.halayang.server.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.server.user.mapper.UserMapper;
import com.halayang.server.user.po.UserPO;
import com.halayang.server.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public boolean saveOrUpdateUser(UserPO userPo) {
        if (StringUtils.isEmpty(userPo.getId())) {
            LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<UserPO>()
                    .eq(UserPO::getLoginName, userPo.getLoginName());
            int count = this.count(wrapper);
            if (count > 0) {
                throw new IllegalArgumentException("用户名已被注册，请换一个");
            }
            userPo.setPassword(BCryptUtils.encode("123456"));
            return this.save(userPo);
        }
        return this.updateById(userPo);
    }
}
