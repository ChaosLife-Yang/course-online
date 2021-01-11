package com.halayang.server.course.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.course.dto.CourseDTO;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CourseContentService;
import com.halayang.server.course.service.CourseService;
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
    @GetMapping("/{id}")
    public ResponseObject<CourseDTO> getOne(@PathVariable String id) {
        CoursePO po = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(po, courseDTO);
        return ResponseResult.success(courseDTO);
    }

    /**
     * 课程分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.course.po.CoursePO>>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<CourseDTO>> courseList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CoursePO> pageInfo = new PageInfo<>(courseService.list());
        List<CoursePO> list = pageInfo.getList();
        List<CourseDTO> dtoList = CopyUtils.copyList(list, CourseDTO.class);
        PageDTO<CourseDTO> page = new PageDTO<CourseDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 课程添加或更新
     * @param courseDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-15 16:07:35
     */
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
