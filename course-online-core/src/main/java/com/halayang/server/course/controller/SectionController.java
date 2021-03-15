package com.halayang.server.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.common.dto.SectionDTO;
import com.halayang.server.course.dto.SectionPageDTO;
import com.halayang.server.course.po.SectionPO;
import com.halayang.server.course.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 小节 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-20 12:28:51
 */
@Api(tags = {"课程小节管理"})
@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    /**
     * 获取小节对象信息
     *
     * @param id 小节id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.course.po.SectionPO>
     * @author YangYudi
     * @date 2020-12-20 12:28:51
     */
    @ApiOperation(value = "小节查询", httpMethod = "GET", notes = "根据id小节详情")
    @GetMapping("/info/{id}")
    public ResponseObject<SectionDTO> getOne(@PathVariable String id) {
        SectionPO po = sectionService.getById(id);
        SectionDTO sectionDTO = new SectionDTO();
        BeanUtils.copyProperties(po, sectionDTO);
        return ResponseResult.success(sectionDTO);
    }

    /**
     * 小节分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.course.po.SectionPO>>
     * @author YangYudi
     * @date 2020-12-20 12:28:51
     */
    @ApiOperation(value = "小节分页查询", httpMethod = "POST", notes = "根据dto查询分页")
    @PostMapping("/list")
    public ResponseObject<PageDTO<SectionDTO>> sectionList(@RequestBody @Validated SectionPageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        LambdaQueryWrapper<SectionPO> wrapper = new LambdaQueryWrapper<SectionPO>()
                .eq(SectionPO::getChapterId, pageDTO.getChapterId())
                .eq(SectionPO::getCourseId, pageDTO.getCourseId())
                .orderByAsc(SectionPO::getSort);
        PageInfo<SectionPO> pageInfo = new PageInfo<>(sectionService.list(wrapper));
        List<SectionPO> list = pageInfo.getList();
        List<SectionDTO> dtoList = CopyUtils.copyList(list, SectionDTO.class);
        PageDTO<SectionDTO> page = new PageDTO<SectionDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setTotal(pageInfo.getTotal())
                .setList(dtoList);
        return ResponseResult.success(page.getTotal(), page);
    }

    /**
     * 小节添加或更新
     *
     * @param sectionDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 12:28:51
     */
    @ApiOperation(value = "新增或更新", httpMethod = "POST", notes = "根据dto查询分页")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated SectionDTO sectionDTO) {
        SectionPO sectionPo = new SectionPO();
        BeanUtils.copyProperties(sectionDTO, sectionPo);
        boolean option = sectionService.saveOrUpdate(sectionPo);
        sectionService.updateCourseTime(sectionPo.getCourseId());
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除小节
     *
     * @param id 小节id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 12:28:51
     */
    @ApiOperation(value = "小节删除", httpMethod = "GET", notes = "根据id删除")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = sectionService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
