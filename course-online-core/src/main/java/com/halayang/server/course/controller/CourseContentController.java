package com.halayang.server.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.vo.PageVO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.dto.CourseContentDTO;
import com.halayang.server.course.po.CourseContentPO;
import com.halayang.server.course.service.CourseContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程内容 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-09 21:59:29
 */
@Api(tags = {"课程内容管理"})
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 获取课程内容对象信息
     *
     * @param id 课程内容id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.courseContent.po.CourseContentPO>
     * @author YangYudi
     * @date 2021-01-09 21:59:29
     */
    @ApiOperation(value = "获取课程内容对象信息", httpMethod = "GET", notes = "获取课程内容对象信息")
    @GetMapping("/info/{id}")
    public ResponseObject<CourseContentDTO> getOne(@PathVariable String id) {
        CourseContentPO po = courseContentService.getById(id);
        CourseContentDTO courseContentDTO = null;
        if (po != null) {
            courseContentDTO = new CourseContentDTO();
            BeanUtils.copyProperties(po, courseContentDTO);
        }
        return ResponseResult.success(courseContentDTO);
    }

    /**
     * 课程内容分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.courseContent.po.CourseContentPO>>
     * @author YangYudi
     * @date 2021-01-09 21:59:29
     */
    @ApiOperation(value = "课程内容分页查询", httpMethod = "POST", notes = "课程内容分页查询")
    @PostMapping("/list")
    public ResponseObject<PageVO<CourseContentDTO>> courseContentList(@RequestBody @Validated PageQueryDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<CourseContentPO> pageInfo = new PageInfo<>(courseContentService.list(new LambdaQueryWrapper<CourseContentPO>().orderByDesc(CourseContentPO::getId)));
        List<CourseContentPO> list = pageInfo.getList();
        List<CourseContentDTO> dtoList = CopyUtils.copyList(list, CourseContentDTO.class);
        PageVO<CourseContentDTO> page = new PageVO<CourseContentDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 课程内容添加或更新
     *
     * @param courseContentDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-09 21:59:29
     */
    @ApiOperation(value = "课程内容添加或更新", httpMethod = "POST", notes = "课程内容添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated CourseContentDTO courseContentDTO) {
        CourseContentPO courseContentPo = new CourseContentPO();
        BeanUtils.copyProperties(courseContentDTO, courseContentPo);
        boolean option = courseContentService.saveOrUpdate(courseContentPo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除课程内容
     *
     * @param id 课程内容id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-09 21:59:29
     */
    @ApiOperation(value = "删除课程内容", httpMethod = "GET", notes = "删除课程内容")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = courseContentService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
