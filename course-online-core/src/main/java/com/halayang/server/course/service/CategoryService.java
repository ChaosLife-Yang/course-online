package com.halayang.server.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.common.dto.CategoryDTO;
import com.halayang.server.course.po.CategoryPO;

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

    /**
     * 获取分类列表，包含子分类
     *
     * @author YangYudi
     * @date 2021/2/1 9:55
     * @return java.util.List<com.halayang.common.dto.CategoryDTO>
     */
    List<CategoryDTO> getCategoryList();

}
