package com.halayang.server.user.service;

import com.halayang.server.user.dto.RoleUserDTO;
import com.halayang.server.user.po.RoleUserPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色用户关联 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface RoleUserService extends IService<RoleUserPO> {

    /**
     * 用户角色多对多新增或修改
     *
     * @param roleUserDTO dto对象
     * @author YangYudi
     * @date 2021/2/19 16:18
     * @return boolean
     */
    boolean saveOrUpdate(RoleUserDTO roleUserDTO);
}
