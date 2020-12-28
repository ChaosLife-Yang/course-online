package com.halayang.server.category.service.impl;

import com.halayang.server.category.po.CategoryPO;
import com.halayang.server.category.mapper.CategoryMapper;
import com.halayang.server.category.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
