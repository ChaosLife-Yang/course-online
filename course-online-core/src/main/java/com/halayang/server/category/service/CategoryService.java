package com.halayang.server.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.category.dto.CategoryDTO;
import com.halayang.server.category.po.CategoryPO;

import java.util.List;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-28
 */
public interface CategoryService extends IService<CategoryPO> {

    List<CategoryDTO> getCategoryList();

}
