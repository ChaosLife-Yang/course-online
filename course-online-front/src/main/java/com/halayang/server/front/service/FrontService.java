package com.halayang.server.front.service;

import com.halayang.common.dto.*;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.feign.CourseCoreFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 18:03           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 18:03
 */
@Service
public class FrontService {

    @Resource
    private CourseCoreFeign coreFeign;

    public CourseDTO getCourseOne(String id) {
        return coreFeign.getCourseOne(id).getData();
    }

    public CourseWebVo getCourseWeb(String id) {
        return coreFeign.getCourseWeb(id).getData();
    }

    public PageDTO<CourseDTO> courseList(PageDTO<CourseDTO> pageDTO) {
        return coreFeign.courseList(pageDTO).getData();
    }

    public List<CategoryDTO> categoryList() {
        return coreFeign.categoryList().getData();
    }

    public PopularAndNewCourseDTO popularAndNewCourseList() {
        return coreFeign.popularAndNewCourseList().getData();
    }

    public TeacherDTO getTeacherOne(String id) {
        return coreFeign.getTeacherOne(id).getData();
    }

}
