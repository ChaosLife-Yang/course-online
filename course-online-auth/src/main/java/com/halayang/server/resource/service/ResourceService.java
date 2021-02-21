package com.halayang.server.resource.service;

import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
public interface ResourceService extends IService<ResourcePO> {

    /**
     * 递归获取所有资源及其子节点
     *
     * @author YangYudi
     * @date 2021/2/18 11:28
     * @return java.util.List<com.halayang.server.resource.dto.ResourceDTO>
     */
    List<ResourceDTO> resourceList();

    /**
     * 递归删除 删除节点及子孙节点
     *
     * @param id 节点id
     * @author YangYudi
     * @date 2021/2/18 16:53
     * @return boolean
     */
    boolean deleteResources(String id);

    /**
     * 获取用户的资源权限
     *
     * @param userId id
     * @author YangYudi
     * @date 2021/2/21 12:09
     * @return java.util.List<com.halayang.server.resource.dto.ResourceAuthorityDTO>
     */
    List<ResourceAuthorityDTO> getPermissionByUserId(String userId);

}
