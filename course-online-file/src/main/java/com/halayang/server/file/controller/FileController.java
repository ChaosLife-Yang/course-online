package com.halayang.server.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageQueryDTO;
import com.halayang.common.vo.PageVO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 文件管理 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-16 12:17:31
 */
@Api(tags = {"文件管理"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 获取文件管理对象信息
     *
     * @param id 文件管理id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.file.po.FilePO>
     * @author YangYudi
     * @date 2021-01-16 12:17:31
     */
    @ApiOperation(value = "获取文件管理对象信息", httpMethod = "GET", notes = "获取文件管理对象信息")
    @GetMapping("/{id}")
    public ResponseObject<FileDTO> getOne(@PathVariable String id) {
        FilePO po = fileService.getById(id);
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(po, fileDTO);
        return ResponseResult.success(fileDTO);
    }

    /**
     * 文件管理分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.dto.PageDTO<com.halayang.server.file.po.FilePO>>
     * @author YangYudi
     * @date 2021-01-16 12:17:31
     */
    @ApiOperation(value = "文件管理分页查询", httpMethod = "POST", notes = "文件管理分页查询")
    @PostMapping("/list")
    public ResponseObject<PageVO<FileDTO>> fileList(@RequestBody @Validated PageQueryDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<FilePO> pageInfo = new PageInfo<>(fileService.list(new LambdaQueryWrapper<FilePO>().orderByDesc(FilePO::getId)));
        List<FilePO> list = pageInfo.getList();
        List<FileDTO> dtoList = CopyUtils.copyList(list, FileDTO.class);
        PageVO<FileDTO> page = new PageVO<FileDTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * 文件管理添加或更新
     *
     * @param fileDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-16 12:17:31
     */
    @ApiOperation(value = "文件管理添加或更新", httpMethod = "POST", notes = "文件管理添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated FileDTO fileDTO) {
        FilePO filePo = new FilePO();
        BeanUtils.copyProperties(fileDTO, filePo);
        boolean option = fileService.saveOrUpdate(filePo);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除文件管理
     *
     * @param id 文件管理id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-01-16 12:17:31
     */
    @ApiOperation(value = "删除文件管理", httpMethod = "GET", notes = "删除文件管理")
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = fileService.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
