package com.halayang.server.course.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.course.dto.CourseCategoryDTO;
import com.halayang.server.course.po.CourseCategoryPO;
import com.halayang.server.course.service.CourseCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15 15:51:39
 */
@RestController
@RequestMapping("/courseCategory")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    /**
     * 获取课程分类对象信息
     *
     * @param id 课程分类id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.courseCategory.po.CourseCategoryPO>
     * @author YangYudi
     * @date 2020-12-15 15:51:39
     */
    @GetMapping("/{id}")
    public ResponseObject<CourseCategoryDTO> getOne(@PathVariable String id) {
        CourseCategoryPO po = courseCategoryService.getById(id);
        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();
        BeanUtils.copyProperties(po, courseCategoryDTO);
        return ResponseResult.success(courseCategoryDTO);
    }

    /**
     * 课程分类分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.courseCategory.po.CourseCategoryPO>>
     * @author YangYudi
     * @date 2020-12-15 15:51:39
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<CourseCategoryPO>> courseCategoryList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CourseCategoryPO> pageInfo = new PageInfo<>(courseCategoryService.list());
        PageDTO<CourseCategoryPO> page = new PageDTO<CourseCategoryPO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(pageInfo.getList());
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 课程分类添加或更新
     *
     * @param courseCategoryDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-15 15:51:39
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated CourseCategoryDTO courseCategoryDTO) {
        CourseCategoryPO courseCategoryPo = new CourseCategoryPO();
        BeanUtils.copyProperties(courseCategoryDTO, courseCategoryPo);
        boolean option = courseCategoryService.saveOrUpdate(courseCategoryPo);
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
     * @date 2020-12-15 15:51:39
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable Long id) {
        boolean option = courseCategoryService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
