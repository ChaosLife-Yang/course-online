package com.halayang.server.role.service;

import com.halayang.server.role.dto.RoleResourceDTO;
import com.halayang.server.role.po.RoleResourcePO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色资源关联 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface RoleResourceService extends IService<RoleResourcePO> {

    /**
     * 角色资源多对多新增或修改
     *
     * @param roleResourceDTO dto对象
     * @author YangYudi
     * @date 2021/2/19 16:17
     * @return boolean
     */
    boolean saveOrUpdate(RoleResourceDTO roleResourceDTO);

}
