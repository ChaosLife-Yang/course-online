package com.halayang.server.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.rbac.dto.UserDTO;
import com.halayang.server.rbac.po.UserPO;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-17
 */
public interface UserService extends IService<UserPO> {

    boolean saveOrUpdateUser(UserDTO userDto);

}
