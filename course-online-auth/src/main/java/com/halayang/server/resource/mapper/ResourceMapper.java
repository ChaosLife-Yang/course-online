package com.halayang.server.resource.mapper;

import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 根据用户id查询用户拥有的权限
     *
     * @param userId 用户id
     * @author YangYudi
     * @date 2021/2/20 17:07
     * @return java.util.List<com.halayang.server.resource.po.ResourcePO>
     */
    List<ResourceAuthorityDTO> getPermissionByUserId(@Param("userId") String userId);
}
