package com.halayang.server.${module}.dto;

import java.io.Serializable;
<#list typeSet as type>
<#if type=='LocalDateTime'>
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ${moduleName}DTO对象
 *
 * @author YangYuDi
 * @since ${time}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "${Domain}DTO对象", description = "${moduleName}DTO数据交互对象")
public class ${Domain}DTO implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list fieldList as field>
    <#if field.nameHump!='isDelete'>
    /**
    * ${field.comment}
    */
    <#if field.javaType=='LocalDateTime'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.javaType} ${field.nameHump};
    </#if>
    </#list>


}