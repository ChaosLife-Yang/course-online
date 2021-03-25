package com.halayang.server.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.CategoryCourseSearchDto;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.dto.PopularAndNewCourseDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.dto.CourseDTO;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CourseContentService;
import com.halayang.server.course.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15 16:07:35
 */
@Api(tags = {"课程管理"})
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 获取课程对象信息
     *
     * @param id 课程id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.course.po.CoursePO>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
    @ApiOperation(value = "根据id", httpMethod = "GET", notes = "获取课程对象信息")
    @GetMapping("/info/{id}")
    public ResponseObject<CourseDTO> getOne(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        CoursePO po = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(po, courseDTO);
        return ResponseResult.success(courseDTO);
    }

    @ApiOperation(value = "获取课程下所有章节和小节", httpMethod = "GET", notes = "根据id获取该课程所有章节和小节")
    @GetMapping("/web/{id}")
    public ResponseObject<CourseWebVo> web(@ApiParam(name = "id", value = "课程id", required = true) @PathVariable String id) {
        return ResponseResult.success(courseService.getCourseWeb(id));
    }

    @ApiOperation(value = "根据分类获取课程", httpMethod = "POST", notes = "根据分类id获取课程列表")
    @PostMapping("/category")
    public ResponseObject<PageDTO<CourseDTO>> getCategoryCourse(@RequestBody CategoryCourseSearchDto categoryCourseSearchDto) {
        PageHelper.startPage(categoryCourseSearchDto.getPage(), 40);
        PageInfo<CourseDTO> pageInfo = new PageInfo<>(courseService.getCourseByCategoryId(categoryCourseSearchDto.getLevel1(), categoryCourseSearchDto.getLevel2()));
        PageDTO<CourseDTO> page = new PageDTO<CourseDTO>()
                .setPage(Long.valueOf(categoryCourseSearchDto.getPage()))
                .setSize(40L)
                .setTotal(pageInfo.getTotal())
                .setPages(pageInfo.getPages())
                .setList(pageInfo.getList());
        return ResponseResult.success(page.getTotal(), page);
    }

    @ApiOperation(value = "热门课程与新上好课", httpMethod = "GET", notes = "热门课程与新上好课")
    @GetMapping("/front")
    public ResponseObject<PopularAndNewCourseDTO> courseList() {
        return ResponseResult.success(courseService.popularAndNewCourse());
    }

    /**
     * 课程分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.course.po.CoursePO>>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
    @ApiOperation(value = "课程分页查询", httpMethod = "POST", notes = "课程分页查询")
    @PostMapping("/list")
    public ResponseObject<PageDTO<CourseDTO>> courseList(@RequestBody @Validated PageDTO<CourseDTO> pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CoursePO> pageInfo = new PageInfo<>(courseService.list(new LambdaQueryWrapper<CoursePO>().orderByDesc(CoursePO::getId)));
        List<CoursePO> list = pageInfo.getList();
        List<CourseDTO> dtoList = CopyUtils.copyList(list, CourseDTO.class);
        PageDTO<CourseDTO> page = new PageDTO<CourseDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setTotal(pageInfo.getTotal())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(page.getTotal(), page);
    }

    /**
     * 课程添加或更新
     *
     * @param courseDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
    @ApiOperation(value = "课程添加或更新", httpMethod = "POST", notes = "课程添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated CourseDTO courseDTO) {
        CoursePO coursePo = new CoursePO();
        BeanUtils.copyProperties(courseDTO, coursePo);
        boolean option = courseService.saveOrUpdate(coursePo);
        if (option) {
            return ResponseResult.success(coursePo.getId());
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除课程
     *
     * @param id 课程id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
    @ApiOperation(value = "删除课程", httpMethod = "GET", notes = "删除课程")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = courseService.removeById(id);
        courseContentService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }

}
