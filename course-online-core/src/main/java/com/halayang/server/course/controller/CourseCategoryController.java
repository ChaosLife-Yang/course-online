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
    @GetMapping("/{id}")
    public ResponseObject<List<String>> getOne(@PathVariable String id) {
        List<String> collect = courseCategoryService.list(new LambdaQueryWrapper<CourseCategoryPO>()
                .eq(CourseCategoryPO::getCourseId, id))
                .stream()
                .map(CourseCategoryPO::getCategoryId)
                .collect(Collectors.toList());
        return ResponseResult.success(collect);
    }

    /**
     * 课程分类分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.courseCategory.po.CourseCategoryPO>>
     * @author YangYudi
     * @date 2020-12-20 15:39:06
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<CourseCategoryDTO>> courseCategoryList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CourseCategoryPO> pageInfo = new PageInfo<>(courseCategoryService.list());
        List<CourseCategoryPO> list = pageInfo.getList();
        List<CourseCategoryDTO> dtoList = CopyUtils.copyList(list, CourseCategoryDTO.class);
        PageDTO<CourseCategoryDTO> page = new PageDTO<CourseCategoryDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 课程分类添加或更新
     *
     * @param courseCategoryDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 15:39:06
     */
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
