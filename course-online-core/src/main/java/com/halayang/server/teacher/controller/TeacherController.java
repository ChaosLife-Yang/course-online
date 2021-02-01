package com.halayang.server.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.teacher.dto.TeacherDTO;
import com.halayang.server.teacher.po.TeacherPO;
import com.halayang.server.teacher.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 教师 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-11 13:37:22
 */
@Api(tags = {"老师管理"})
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 获取教师对象信息
     *
     * @param id 教师id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.teacher.po.TeacherPO>
     * @author YangYudi
     * @date 2021-01-11 13:37:22
     */
    @ApiOperation(value = "获取教师对象信息", httpMethod = "GET", notes = "获取教师对象信息")
    @GetMapping("/{id}")
    public ResponseObject<TeacherDTO> getOne(@PathVariable String id) {
        TeacherPO po = teacherService.getById(id);
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(po, teacherDTO);
        return ResponseResult.success(teacherDTO);
    }

    /**
     * 教师分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.teacher.po.TeacherPO>>
     * @author YangYudi
     * @date 2021-01-11 13:37:22
     */
    @ApiOperation(value = "教师分页查询", httpMethod = "POST", notes = "教师分页查询")
    @PostMapping("/list")
    public ResponseObject<PageDTO<TeacherDTO>> teacherList(@RequestBody @Validated PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<TeacherPO> pageInfo = new PageInfo<>(teacherService.list(new LambdaQueryWrapper<TeacherPO>().orderByDesc(TeacherPO::getId)));
        List<TeacherPO> list = pageInfo.getList();
        List<TeacherDTO> dtoList = CopyUtils.copyList(list, TeacherDTO.class);
        PageDTO<TeacherDTO> page = new PageDTO<TeacherDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 获取所有老师信息
     *
     * @author YangYudi
     * @date 2021/1/11 16:26
     * @return com.halayang.common.utils.response.ResponseObject<java.util.List < com.halayang.server.teacher.dto.TeacherDTO>>
     */
    @ApiOperation(value = "获取所有老师信息", httpMethod = "GET", notes = "获取所有老师信息")
    @GetMapping("/all")
    public ResponseObject<List<TeacherDTO>> all(){
        List<TeacherPO> list = teacherService.list(new LambdaQueryWrapper<TeacherPO>().orderByDesc(TeacherPO::getId));
        List<TeacherDTO> dtoList = CopyUtils.copyList(list, TeacherDTO.class);
        return ResponseResult.success(dtoList.size(), dtoList);
    }

    /**
     * 教师添加或更新
     *
     * @param teacherDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-11 13:37:22
     */
    @ApiOperation(value = "教师添加或更新", httpMethod = "POST", notes = "教师添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated TeacherDTO teacherDTO) {
        TeacherPO teacherPo = new TeacherPO();
        BeanUtils.copyProperties(teacherDTO, teacherPo);
        if (StringUtils.isEmpty(teacherPo.getId())){
            if (teacherService.count(new LambdaQueryWrapper<TeacherPO>().eq(TeacherPO::getNickname,teacherPo.getNickname()))>0){
                return ResponseResult.error("该昵称已被注册");
            }
            if(teacherService.save(teacherPo)) {
                return ResponseResult.success();
            } else {
                return ResponseResult.error();
            }
        }
        boolean option = teacherService.saveOrUpdate(teacherPo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除教师
     *
     * @param id 教师id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-11 13:37:22
     */
    @ApiOperation(value = "删除教师", httpMethod = "GET", notes = "删除教师")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = teacherService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
