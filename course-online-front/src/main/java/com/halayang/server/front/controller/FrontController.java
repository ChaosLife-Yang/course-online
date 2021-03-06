package com.halayang.server.front.controller;

import com.halayang.common.dto.*;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.common.vo.PageVO;
import com.halayang.server.front.service.FrontService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 18:08           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 18:08
 */
@Api(tags = {"前台数据展示"})
@RestController
@RequestMapping("/display")
public class FrontController {

    @Autowired
    private FrontService frontService;

    @ApiOperation(value = "获取课程信息", httpMethod = "GET", notes = "根据课程id获取课程信息")
    @GetMapping("/course/{id}")
    public ResponseObject<CourseDTO> getCourseOne(@PathVariable String id) {
        return ResponseResult.success(frontService.getCourseOne(id));
    }

    @ApiOperation(value = "获取课程详情", httpMethod = "GET", notes = "根据课程id获取课程详情")
    @GetMapping("/courseContent/{id}")
    public ResponseObject<CourseContentDTO> getCourseContent(@PathVariable String id) {
        return ResponseResult.success(frontService.getCourseContentOne(id));
    }

    @ApiOperation(value = "获取课程下所有章节和小节", httpMethod = "GET", notes = "根据课程id获取该课程所有章节和小节")
    @GetMapping("/courseWeb/{id}")
    public ResponseObject<CourseWebVo> courseWeb(@PathVariable String id) {
        return ResponseResult.success(frontService.getCourseWeb(id));
    }

    @ApiOperation(value = "根据分类获取课程", httpMethod = "GET", notes = "根据分类id获取课程列表")
    @PostMapping("/categoryCourse")
    public ResponseObject<PageVO<CourseDTO>> getCategoryCourse(@RequestBody CategoryCourseSearchDto categoryCourseSearchDto) {
        return ResponseResult.success(frontService.getCategoryCourse(categoryCourseSearchDto));
    }

    @ApiOperation(value = "课程列表展示", httpMethod = "POST", notes = "课程列表展示")
    @PostMapping("/coursePage/")
    public ResponseObject<PageVO<CourseDTO>> coursePage(@RequestBody PageVO<CourseDTO> pageDTO) {
        PageVO<CourseDTO> page = frontService.courseList(pageDTO);
        return ResponseResult.success(page.getTotal(), page);
    }

    @ApiOperation(value = "热门课程和新上好课", httpMethod = "GET", notes = "热门课程和新上好课")
    @GetMapping("/coursePop")
    public ResponseObject<PopularAndNewCourseDTO> popularAndNewCourseList() {
        return ResponseResult.success(frontService.popularAndNewCourseList());
    }

    @ApiOperation(value = "课程分类", httpMethod = "GET", notes = "课程分类")
    @GetMapping("/category")
    public ResponseObject<List<CategoryDTO>> categoryList() {
        return ResponseResult.success(frontService.categoryList());
    }

    @ApiOperation(value = "获取老师信息", httpMethod = "GET", notes = "根据老师id获取老师信息")
    @GetMapping("/teacher/{id}")
    public ResponseObject<TeacherDTO> getCourseWeb(@PathVariable String id) {
        return ResponseResult.success(frontService.getTeacherOne(id));
    }


}
