package com.halayang.feign;

import com.halayang.common.dto.CourseDTO;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.dto.PopularAndNewCourseDTO;
import com.halayang.common.dto.TeacherDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.feign.factory.CourseCoreFeignFallbackFactory;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/course/web/{id}")
    ResponseObject<CourseWebVo> getCourseWeb(@PathVariable String id);

    @PostMapping("/course/list")
    ResponseObject<PageDTO<CourseDTO>> courseList(@RequestBody PageDTO<CourseDTO> pageDTO);

    @GetMapping("/course/front")
    ResponseObject<PopularAndNewCourseDTO> popularAndNewCourseList();

    @GetMapping("/teacher/info/{id}")
    ResponseObject<TeacherDTO> getTeacherOne(@PathVariable String id);

}
