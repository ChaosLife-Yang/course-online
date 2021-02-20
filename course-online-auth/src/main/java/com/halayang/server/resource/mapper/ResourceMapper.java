package com.halayang.server.resource.mapper;

import com.halayang.server.resource.po.ResourcePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface ResourceMapper extends BaseMapper<ResourcePO> {
    List<ResourcePO> getPermissionByUserId();
}
