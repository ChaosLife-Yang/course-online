package com.halayang.feign;

import com.halayang.common.dto.*;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.common.vo.PageVO;
import com.halayang.feign.factory.CourseCoreFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/13 21:35           1.0
 * @program course-online
 * @description
 * @create 2021/3/13 21:35
 */
@FeignClient(name = "course-online-core", contextId = "coreFeign", fallbackFactory = CourseCoreFeignFallbackFactory.class)
@Component("coreFeign")
public interface CourseCoreFeign {

    @GetMapping("/course/info/{id}")
    ResponseObject<CourseDTO> getCourseOne(@PathVariable String id);

    @GetMapping("/courseContent/info/{id}")
    ResponseObject<CourseContentDTO> getCourseContent(@PathVariable String id);

    @GetMapping("/course/web/{id}")
    ResponseObject<CourseWebVo> getCourseWeb(@PathVariable String id);

    @PostMapping("/course/list")
    ResponseObject<PageVO<CourseDTO>> courseList(@RequestBody PageVO<CourseDTO> pageDTO);

    @GetMapping("/course/front")
    ResponseObject<PopularAndNewCourseDTO> popularAndNewCourseList();

    @PostMapping("/course/category")
    ResponseObject<PageVO<CourseDTO>> getCategoryCourse(@RequestBody CategoryCourseSearchDto categoryCourseSearchDto);

    @GetMapping("/teacher/info/{id}")
    ResponseObject<TeacherDTO> getTeacherOne(@PathVariable String id);

    @GetMapping("/category/list")
    ResponseObject<List<CategoryDTO>> categoryList();
}
