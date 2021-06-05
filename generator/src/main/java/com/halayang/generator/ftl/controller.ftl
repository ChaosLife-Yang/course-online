package com.halayang.server.${module}.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halayang.common.dto.PageQueryDTO;
import com.halayang.common.vo.PageVO;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.${module}.dto.${classNamePrefix}DTO;
import com.halayang.server.${module}.po.${classNamePrefix}PO;
import com.halayang.server.${module}.service.${classNamePrefix}Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * ${moduleName} 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since ${time}
 */
@RestController
@RequestMapping("/${domain}")
public class ${classNamePrefix}Controller {

    @Autowired
    private ${classNamePrefix}Service ${domain}Service;

    /**
     * 获取${moduleName}对象信息
     *
     * @param id ${moduleName}id
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.server.${domain}.po.${classNamePrefix}PO>
     * @author YangYudi
     * @date ${time}
     */
    @GetMapping("/{id}")
    public ResponseObject<${classNamePrefix}DTO> getOne(@PathVariable String id) {
        ${classNamePrefix}PO po = ${domain}Service.getById(id);
        ${classNamePrefix}DTO ${domain}DTO = new ${classNamePrefix}DTO();
        BeanUtils.copyProperties(po, ${domain}DTO);
        return ResponseResult.success(${domain}DTO);
    }

    /**
     * ${moduleName}分页查询
     *
     * @param pageDTO 分页数据
     * @return com.halayang.common.utils.response.ResponseObject<com.halayang.common.vo.PageVO<com.halayang.server.${domain}.po.${classNamePrefix}PO>>
     * @author YangYudi
     * @date ${time}
     */
    @PostMapping("/list")
    public ResponseObject<PageVO<${classNamePrefix}DTO>> ${domain}List(@RequestBody @Validated PageQueryDTO pageDTO) {
        //startPage方法往下遇到的第一个sql语句执行分页操作
        PageHelper.startPage(pageDTO.getPage().intValue(), pageDTO.getSize().intValue());
        PageInfo<${classNamePrefix}PO> pageInfo = new PageInfo<>(${domain}Service.list(new LambdaQueryWrapper<${classNamePrefix}PO>().orderByDesc(${classNamePrefix}PO::getId)));
        List<${classNamePrefix}PO> list = pageInfo.getList();
        List<${classNamePrefix}DTO> dtoList = CopyUtils.copyList(list, ${classNamePrefix}DTO.class);
        PageDTO<${classNamePrefix}DTO> page = new PageDTO<${classNamePrefix}DTO>()
                .setPage(pageDTO.getPage())
                .setSize(pageDTO.getSize())
                .setPages(pageInfo.getPages())
                .setList(dtoList);
        return ResponseResult.success(pageInfo.getTotal(), page);
    }

    /**
     * ${moduleName}添加或更新
     *
     * @param ${domain}DTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date ${time}
     */
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated ${classNamePrefix}DTO ${domain}DTO) {
        ${classNamePrefix}PO ${domain}Po = new ${classNamePrefix}PO();
        BeanUtils.copyProperties(${domain}DTO, ${domain}Po);
        boolean option = ${domain}Service.saveOrUpdate(${domain}Po);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除${moduleName}
     *
     * @param id ${moduleName}id
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date ${time}
     */
    @GetMapping("/delete/{id}")
    public ResponseObject<String> delete(@PathVariable String id) {
        boolean option = ${domain}Service.removeById(id);
        if (option) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
