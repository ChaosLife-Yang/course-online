package com.halayang.server.front.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.dto.PopularAndNewCourseDTO;
import com.halayang.common.enums.CourseStatusEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.course.dto.CategoryDTO;
import com.halayang.common.dto.CourseDTO;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CategoryService;
import com.halayang.server.course.service.CourseContentService;
import com.halayang.server.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/12 17:25           1.0
 * @program course-online
 * @description
 * @create 2021/3/12 17:25
 */
@Service
public class DisplayService {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseContentService courseContentService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 热门课程和新课程列表展示
     *
     * @author YangYudi
     * @date 2021/3/12 17:40
     * @return com.halayang.server.front.dto.PopularAndNewCourseDTO
     */
    public PopularAndNewCourseDTO popularAndNewCourse() {
        List<CoursePO> newPoList = courseService.list(new LambdaQueryWrapper<CoursePO>()
                .eq(CoursePO::getStatus, CourseStatusEnum.PUBLISH.getCode())
                .orderByDesc(CoursePO::getCreateTime)
                .last("limit 8"));
        List<CoursePO> popularPoList = courseService.list(new LambdaQueryWrapper<CoursePO>()
                .eq(CoursePO::getStatus, CourseStatusEnum.PUBLISH.getCode())
                .orderByDesc(CoursePO::getEnroll)
                .last("limit 8"));

        return new PopularAndNewCourseDTO()
                .setPopular(CopyUtils.copyList(popularPoList, CourseDTO.class))
                .setFresh(CopyUtils.copyList(newPoList, CourseDTO.class));
    }

}
