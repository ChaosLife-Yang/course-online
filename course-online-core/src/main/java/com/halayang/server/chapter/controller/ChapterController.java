package com.halayang.server.chapter.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.chapter.po.ChapterPO;
import com.halayang.server.chapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 大章 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-11
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
     * @date 2020/12/15 13:31
     */
    @GetMapping("/{id}")
    public ResponseObject<ChapterPO> getOne(@PathVariable String id) {
        ChapterPO po = chapterService.getById(id);
        return ResponseResult.success(po);
    }

    /**
     * 章节分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO < com.halayang.server.chapter.po.ChapterPO>>
     * @author YangYudi
     * @date 2020/12/13 16:38
     */
    @PostMapping("/list")
    public ResponseObject<PageDTO<ChapterPO>> chapterList(@RequestBody PageDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<ChapterPO> pageInfo = new PageInfo<>(chapterService.list());
        PageDTO<ChapterPO> page = new PageDTO<ChapterPO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(pageInfo.getList());
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 章节添加或更新
     *
     * @param chapterPo 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020/12/13 16:38
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated ChapterPO chapterPo) {
        boolean option = chapterService.saveOrUpdate(chapterPo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除章节
     *
     * @param id 大章id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020/12/13 16:38
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

    /**
     * 更新
     *
     * @param chapterPo
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2020/12/13 16:38
     */
    @PostMapping("/update")
    public ResponseObject<String> update(@RequestBody ChapterPO chapterPo) {
        boolean option = chapterService.updateById(chapterPo);
        if (option) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.error();
        }
    }

}
