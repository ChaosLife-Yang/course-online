package com.halayang.server.category.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.category.dto.CategoryDTO;
import com.halayang.server.category.po.CategoryPO;
import com.halayang.server.category.service.CategoryService;
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
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取课程分类对象信息
     *
     * @param id 课程分类id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.category.po.CategoryPO>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
    @GetMapping("/{id}")
    public ResponseObject<CategoryDTO> getOne(@PathVariable String id) {
        CategoryPO po = categoryService.getById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(po, categoryDTO);
        return ResponseResult.success(categoryDTO);
    }

    /**
     * 课程分类分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.category.po.CategoryPO>>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<CategoryDTO>> categoryList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CategoryPO> pageInfo = new PageInfo<>(categoryService.list());
        List<CategoryPO> list = pageInfo.getList();
        List<CategoryDTO> dtoList = CopyUtils.copyList(list, CategoryDTO.class);
        PageDTO<CategoryDTO> page = new PageDTO<CategoryDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 课程分类添加或更新
     *
     * @param categoryDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-28 15:22:50
     */
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
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable Long id) {
        boolean option = categoryService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
