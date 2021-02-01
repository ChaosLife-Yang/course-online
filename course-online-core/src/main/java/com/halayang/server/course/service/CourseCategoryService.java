package com.halayang.server.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.course.dto.CourseCategoryDTO;
import com.halayang.server.course.po.CourseCategoryPO;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
public interface CourseCategoryService extends IService<CourseCategoryPO> {

    /**
     * 更新或保存分课程类
     *
     * @param courseCategoryDTO 课程分类DTO数据交互对象
     * @author YangYudi
     * @date 2021/2/1 9:56
     * @return boolean
     */
    boolean saveOrUpdateCourseCategories(CourseCategoryDTO courseCategoryDTO);


}
