package com.halayang.config.oauth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.service.ResourceService;
import com.halayang.server.user.po.UserPO;
import com.halayang.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 设置动态用户信息
 *
 * @author YUDI
 * @date 2019/11/10
 */
@Service("myUserDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        根据用户名称查询数据库信息
        UserPO user = userService.getOne(new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getLoginName, username));
//        如果数据库中存在该用户名则进行以下操作
        if (!ObjectUtils.isEmpty(user)) {
//        底层会根据数据库查询用户信息，判断密码是否正确
            List<ResourceAuthorityDTO> list = resourceService.getPermissionByUserId(user.getId());
            if (!CollectionUtils.isEmpty(list)) {
                // 设置用户权限
                user.setPermissions(
                        list.stream()
                                .filter(dto -> !StringUtils.isEmpty(dto.getPermissionValue()))
                                .collect(Collectors.toList())
                );
            }
            return user;
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

}
