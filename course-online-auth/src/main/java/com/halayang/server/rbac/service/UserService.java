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

    /**
     * 新增或更新用户
     *
     * @param userDto 用户dto
     * @author YangYudi
     * @date 2021/2/1 10:02
     * @return boolean
     */
    boolean saveOrUpdateUser(UserDTO userDto);

}
