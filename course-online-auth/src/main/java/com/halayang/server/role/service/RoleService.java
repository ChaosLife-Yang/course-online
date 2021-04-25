package com.halayang.server.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.role.po.RolePO;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface RoleService extends IService<RolePO> {

    /**
     * 获取用户角色
     *
     * @param userId id
     * @return java.util.List<java.lang.String>
     * @author YangYudi
     * @date 2021/2/21 12:11
     */
    List<String> getRoleByUserId(String userId);

}
