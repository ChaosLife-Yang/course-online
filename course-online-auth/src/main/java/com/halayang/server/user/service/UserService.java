package com.halayang.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.user.po.UserPO;

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
     * @author YangYudi
     * @date 2021/2/1 10:02
     * @return boolean
     */
    boolean saveOrUpdateUser(UserPO userPO);

}
