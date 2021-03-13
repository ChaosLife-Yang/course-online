package com.halayang.server.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.course.dto.CourseCategoryDTO;
import com.halayang.server.course.po.CourseCategoryPO;
import com.halayang.server.course.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-20 15:39:06
 */

@Api(tags = {"课程分类管理管理"})
@RestController
@RequestMapping("/courseCategory")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    /**
     * 通过课程id获取分类id列表
     *
     * @param id 课程id
     * @return ResponseObject<List < String>>
     * @author YangYudi
     * @date 2020-12-20 15:39:06
     */
    @ApiOperation(value = "通过课程id获取分类id列表", httpMethod = "GET", notes = "通过课程id获取分类id列表")
    @GetMapping("/info/{id}")
    public ResponseObject<List<String>> getOne(@PathVariable String id) {
        List<String> collect = courseCategoryService.list(new LambdaQueryWrapper<CourseCategoryPO>()
                .eq(CourseCategoryPO::getCourseId, id))
                .stream()
                .map(CourseCategoryPO::getCategoryId)
                .collect(Collectors.toList());
        return ResponseResult.success(collect);
    }

    /**
     * 课程分类添加或更新
     *
     * @param courseCategoryDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 15:39:06
     */
    @ApiOperation(value = "课程分类添加或更新", httpMethod = "POST", notes = "课程分类添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated CourseCategoryDTO courseCategoryDTO) {
        boolean option = courseCategoryService.saveOrUpdateCourseCategories(courseCategoryDTO);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除课程分类
     *
     * @param id 课程分类id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 15:39:06
     */
    @ApiOperation(value = "删除课程分类", httpMethod = "GET", notes = "删除课程分类")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = courseCategoryService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
