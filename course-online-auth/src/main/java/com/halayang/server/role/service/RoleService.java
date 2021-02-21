package com.halayang.server.role.service;

import com.halayang.server.role.po.RolePO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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
     * @author YangYudi
     * @date 2021/2/21 12:11
     * @return java.util.List<java.lang.String>
     */
    List<String> getRoleByUserId(String userId);

}
