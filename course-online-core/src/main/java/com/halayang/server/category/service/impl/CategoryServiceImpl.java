package com.halayang.server.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.category.dto.CategoryDTO;
import com.halayang.server.category.mapper.CategoryMapper;
import com.halayang.server.category.po.CategoryPO;
import com.halayang.server.category.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryPO> implements CategoryService {

    @Override
    public List<CategoryDTO> getCategoryList() {
        List<CategoryPO> list = this.list(new LambdaQueryWrapper<CategoryPO>()
                .orderByDesc(CategoryPO::getId));
        List<CategoryDTO> result = new ArrayList<>();
        ArrayList<CategoryDTO> children;
        CategoryDTO parentDto;
        CategoryDTO childDto;
        //遍历所有节点找出一级节点
        for (CategoryPO parentPo : list) {
            children = new ArrayList<>();
            parentDto = new CategoryDTO();
            BeanUtils.copyProperties(parentPo, parentDto);
            //再遍历一遍所有节点找出一级节点的子节点
            for (CategoryPO childPo : list) {
                //设置子节点
                if (!StringUtils.isEmpty(childPo.getParent()) &&
                        childPo.getParent().equals(parentPo.getId())) {
                    childDto = new CategoryDTO();
                    BeanUtils.copyProperties(childPo, childDto);
                    children.add(childDto);
                }
            }
            parentDto.setChildren(children);
            result.add(parentDto);
        }
        //过滤掉二级节点
        return result.stream()
                .filter(c -> StringUtils.isEmpty(c.getParent()))
                .collect(Collectors.toList());
    }

}
