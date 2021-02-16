package com.halayang.server.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.server.rbac.dto.UserDTO;
import com.halayang.server.rbac.mapper.UserMapper;
import com.halayang.server.rbac.po.UserPO;
import com.halayang.server.rbac.service.UserService;
import org.springframework.beans.BeanUtils;
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
    public boolean saveOrUpdateUser(UserDTO userDto) {
        if (StringUtils.isEmpty(userDto.getId())) {
            LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<UserPO>()
                    .eq(UserPO::getLoginName, userDto.getLoginName());
            int count = this.count(wrapper);
            if (count > 0) {
                throw new IllegalArgumentException("用户名已被注册，请换一个");
            }
            //对密码进行加密
            userDto.setPassword(BCryptUtils.encode(userDto.getPassword()));
        }
        UserPO userPo = new UserPO();
        BeanUtils.copyProperties(userDto, userPo);
        return this.saveOrUpdate(userPo);
    }
}
