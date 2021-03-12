package com.halayang.server.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.enums.CourseStatusEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.course.dto.CategoryDTO;
import com.halayang.server.course.dto.CourseDTO;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CategoryService;
import com.halayang.server.course.service.CourseContentService;
import com.halayang.server.course.service.CourseService;
import com.halayang.server.front.service.DisplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/12 17:04           1.0
 * @program course-online
 * @description
 * @create 2021/3/12 17:04
 */
@Api(tags = {"前台页面数据展示"})
@RequestMapping("/display")
@RestController
public class DisplayController {

    @Autowired
    private DisplayService displayService;

    @ApiOperation(value = "分类展示", httpMethod = "GET", notes = "前台分类展示")
    @GetMapping("/category")
    public ResponseObject<List<CategoryDTO>> categoryList() {
        return ResponseResult.success(displayService.categoryList());
    }

    @ApiOperation(value = "热门课程与新上好课", httpMethod = "GET", notes = "热门课程与新上好课")
    @GetMapping("/course")
    public ResponseObject<Object> courseList() {
        return ResponseResult.success(displayService.popularAndNewCourse());
    }

}
