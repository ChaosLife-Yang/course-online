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

    boolean saveOrUpdateCourseCategories(CourseCategoryDTO courseCategoryDTO);


}
