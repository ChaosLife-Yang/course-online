package com.halayang.server.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;

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
     * @return java.util.List<com.halayang.server.resource.dto.ResourceDTO>
     * @author YangYudi
     * @date 2021/2/18 11:28
     */
    List<ResourceDTO> resourceList();

    /**
     * 递归删除 删除节点及子孙节点
     *
     * @param id 节点id
     * @return boolean
     * @author YangYudi
     * @date 2021/2/18 16:53
     */
    boolean deleteResources(String id);

    /**
     * 获取用户的资源权限
     *
     * @param userId id
     * @return java.util.List<com.halayang.server.resource.dto.ResourceAuthorityDTO>
     * @author YangYudi
     * @date 2021/2/21 12:09
     */
    List<ResourceAuthorityDTO> getPermissionByUserId(String userId);

}
