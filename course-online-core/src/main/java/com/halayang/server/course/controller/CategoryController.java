package com.halayang.server.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.dto.CategoryDTO;
import com.halayang.server.course.po.CategoryPO;
import com.halayang.server.course.po.CourseCategoryPO;
import com.halayang.server.course.service.CategoryService;
import com.halayang.server.course.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-28 15:22:50
 */
@Api(tags = {"课程分类管理"})
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseCategoryService courseCategoryService;

    /**
     * 获取课程分类对象信息
     *
     * @param id 课程分类id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.course.po.CategoryPO>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
    @ApiOperation(value = "获取课程分类对象信息", httpMethod = "GET", notes = "获取课程分类对象信息")
    @GetMapping("/info/{id}")
    public ResponseObject<CategoryDTO> getOne(@PathVariable String id) {
        CategoryPO po = categoryService.getById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(po, categoryDTO);
        return ResponseResult.success(categoryDTO);
    }

    /**
     * 课程分类查询
     *
     * @return ResponseObject<List < CategoryDTO>>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
    @ApiOperation(value = "课程分类查询", httpMethod = "GET", notes = "课程分类查询")
    @GetMapping("/list")
    public ResponseObject<List<CategoryDTO>> categoryList() {
        List<CategoryDTO> dtoList = categoryService.getCategoryList();
        return ResponseResult.success(dtoList);
    }

    /**
     * 课程分类添加或更新
     *
     * @param categoryDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
    @ApiOperation(value = "课程分类添加或更新", httpMethod = "POST", notes = "课程分类添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated CategoryDTO categoryDTO) {
        CategoryPO categoryPo = new CategoryPO();
        BeanUtils.copyProperties(categoryDTO, categoryPo);
        boolean option = categoryService.saveOrUpdate(categoryPo);
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
     * @date 2020-12-28 15:22:50
     */
    @ApiOperation(value = "删除课程分类", httpMethod = "GET", notes = "删除课程分类")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = categoryService.removeById(id);
        courseCategoryService.remove(new LambdaQueryWrapper<CourseCategoryPO>()
                .eq(CourseCategoryPO::getCategoryId, id));
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
