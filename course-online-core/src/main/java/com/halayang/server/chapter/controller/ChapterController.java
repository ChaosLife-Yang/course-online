package com.halayang.server.chapter.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.chapter.dto.ChapterDTO;
import com.halayang.server.chapter.dto.ChapterPageDTO;
import com.halayang.server.chapter.po.ChapterPO;
import com.halayang.server.chapter.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 大章 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-20 12:34:23
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 获取大章对象信息
     *
     * @param id 大章id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.chapter.po.ChapterPO>
     * @author YangYudi
     * @date 2020-12-20 12:34:23
     */
    @GetMapping("/{id}")
    public ResponseObject<ChapterDTO> getOne(@PathVariable String id) {
        ChapterPO po = chapterService.getById(id);
        ChapterDTO chapterDTO = new ChapterDTO();
        BeanUtils.copyProperties(po, chapterDTO);
        return ResponseResult.success(chapterDTO);
    }

    /**
     * 大章分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.chapter.po.ChapterPO>>
     * @author YangYudi
     * @date 2020-12-20 12:34:23
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<ChapterDTO>> chapterList(@RequestBody @Validated ChapterPageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        //根据courseId查询
        LambdaQueryWrapper<ChapterPO> wrapper = new LambdaQueryWrapper<ChapterPO>().eq(ChapterPO::getCourseId, pageDTO.getCourseId());
        PageInfo<ChapterPO> pageInfo = new PageInfo<>(chapterService.list(wrapper));
        List<ChapterPO> list = pageInfo.getList();
        List<ChapterDTO> dtoList = CopyUtils.copyList(list, ChapterDTO.class);
        PageDTO<ChapterDTO> page = new PageDTO<ChapterDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 大章添加或更新
     *
     * @param chapterDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 12:34:23
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated ChapterDTO chapterDTO) {
        ChapterPO chapterPo = new ChapterPO();
        BeanUtils.copyProperties(chapterDTO, chapterPo);
        boolean option = chapterService.saveOrUpdate(chapterPo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除大章
     *
     * @param id 大章id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020-12-20 12:34:23
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable Long id) {
        boolean option = chapterService.removeById(id);
        if (option) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error();
        }
    }


}
