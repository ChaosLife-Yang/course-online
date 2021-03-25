package com.halayang.feign.fallback;

import com.halayang.common.dto.*;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.feign.CourseCoreFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 13:39           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 13:39
 */
@Component
public class CourseCoreFeignFallback implements CourseCoreFeign {

    private static final String ERR_MSG = "请求有误";

    @Override
    public ResponseObject<CourseDTO> getCourseOne(String id) {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<CourseWebVo> getCourseWeb(String id) {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<PopularAndNewCourseDTO> popularAndNewCourseList() {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<PageDTO<CourseDTO>> getCategoryCourse(CategoryCourseSearchDto categoryCourseSearchDto ) {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<TeacherDTO> getTeacherOne(String id) {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<List<CategoryDTO>> categoryList() {
        throw new IllegalArgumentException(ERR_MSG);
    }

    @Override
    public ResponseObject<PageDTO<CourseDTO>> courseList(PageDTO<CourseDTO> pageDTO) {
        throw new IllegalArgumentException(ERR_MSG);
    }
}
