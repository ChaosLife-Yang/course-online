package com.halayang.server.role.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * roleDTO对象
 *
 * @author YangYuDi
 * @since 2021-02-19 13:05:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RoleDTO对象", description = "roleDTO数据交互对象")
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 角色
    */
    @ApiModelProperty(value = "角色")
    private String name;
    /**
    * 描述
    */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "")
    private LocalDateTime createTime;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "")
    private LocalDateTime editTime;


}