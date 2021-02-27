package com.halayang.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.user.dto.RefreshTokenDto;
import com.halayang.server.user.dto.UserLoginDto;
import com.halayang.server.user.po.UserPO;

import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-17
 */
public interface UserService extends IService<UserPO> {

    /**
     * 新增或更新用户
     *
     * @param userPO 用户po
     * @return boolean
     * @author YangYudi
     * @date 2021/2/1 10:02
     */
    boolean saveOrUpdateUser(UserPO userPO);

    Map<String, Object> login(UserLoginDto userLoginDto);

    Map<String, Object> refreshToken(RefreshTokenDto refreshTokenDto);

}
