package com.halayang.server.resource.service.impl;

import com.halayang.common.utils.CopyUtils;
import com.halayang.server.resource.dto.ResourceDTO;
import com.halayang.server.resource.po.ResourcePO;
import com.halayang.server.resource.mapper.ResourceMapper;
import com.halayang.server.resource.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourcePO> implements ResourceService {

    @Override
    public List<ResourceDTO> resourceList() {
        //先把资源信息先全部查出来
        List<ResourcePO> poList = this.list();
        List<ResourceDTO> resources = CopyUtils.copyList(poList, ResourceDTO.class);
        //只保留一级节点(父节点为空的节点) 用于页面展示
        List<ResourceDTO> collect = resources.stream()
                .filter(r -> StringUtils.isEmpty(r.getParent()))
                .collect(Collectors.toList());
        //获取子节点及子节点的子节点
        return collect
                .stream()
                .map(resource -> getChildren(resource, resources))
                .collect(Collectors.toList());
    }

    /**
     * 对一级节点设置子节点
     *
     * @param resource dto对象
     * @param tree     所有节点
     * @return com.halayang.server.resource.dto.ResourceDTO
     * @author YangYudi
     * @date 2021/2/18 17:45
     */
    private ResourceDTO getChildren(ResourceDTO resource, List<ResourceDTO> tree) {
        //查找子节点
        resource.setChildren(getChildrenById(resource.getId(), tree));
        return resource;
    }

    /**
     * 根据id递归查找子孙节点
     *
     * @param id   节点id
     * @param tree 所有节点
     * @return java.util.List<com.halayang.server.resource.dto.ResourceDTO>
     * @author YangYudi
     * @date 2021/2/18 16:37
     */
    private List<ResourceDTO> getChildrenById(String id, List<ResourceDTO> tree) {
        return tree.stream()
                .filter(resourceDTO -> resourceDTO.getParent().equals(id))
                .map(resourceDTO -> resourceDTO.setChildren(getChildrenById(resourceDTO.getId(), tree)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteResources(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        //先查询所有
        List<ResourcePO> poList = this.list();
        //根据节点id 查询子孙节点id 保存到一个list上
        getChildrenIds(id, poList, ids);
        return this.removeByIds(ids);
    }

    /**
     * 根据当前节点id递归获取子孙节点id列表 保存到一个list上
     *
     * @param id   id
     * @param list 所有节点
     * @param ids  结果集
     * @author YangYudi
     * @date 2021/2/18 17:22
     */
    private void getChildrenIds(String id, List<ResourcePO> list, List<String> ids) {
        list.forEach(resource -> {
            if (resource.getParent().equals(id)) {
                ids.add(resource.getId());
                getChildrenIds(resource.getId(), list, ids);
            }
        });
    }

}
