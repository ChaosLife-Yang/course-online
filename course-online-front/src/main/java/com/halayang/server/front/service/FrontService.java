package com.halayang.server.front.service;

import com.halayang.common.dto.*;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.common.vo.PageVO;
import com.halayang.feign.CourseCoreFeign;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
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

    public CourseContentDTO getCourseContentOne(String id) {
        return coreFeign.getCourseContent(id).getData();
    }

    public CourseWebVo getCourseWeb(String id) {
        return coreFeign.getCourseWeb(id).getData();
    }

    public PageVO<CourseDTO> courseList(PageVO<CourseDTO> pageDTO) {
        return coreFeign.courseList(pageDTO).getData();
    }

    public PageVO<CourseDTO> getCategoryCourse(CategoryCourseSearchDto categoryCourseSearchDto) {
        return coreFeign.getCategoryCourse(categoryCourseSearchDto).getData();
    }

    @Cacheable(cacheNames = "list", key = "'category'", unless = "#result==null")
    public List<CategoryDTO> categoryList() {
        return coreFeign.categoryList().getData();
    }

    @Cacheable(cacheNames = "list", key = "'popularAndNew'", unless = "#result==null")
    public PopularAndNewCourseDTO popularAndNewCourseList() {
        return coreFeign.popularAndNewCourseList().getData();
    }

    public TeacherDTO getTeacherOne(String id) {
        return coreFeign.getTeacherOne(id).getData();
    }

}
