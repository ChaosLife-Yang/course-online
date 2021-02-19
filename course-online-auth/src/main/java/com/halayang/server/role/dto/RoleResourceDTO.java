package com.halayang.server.role.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色资源DTO对象
 *
 * @author YangYuDi
 * @since 2021-02-19 14:36:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RoleResourceDTO对象", description = "角色资源DTO数据交互对象")
public class RoleResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 角色|id
    */
    @ApiModelProperty(value = "角色|id")
    private String roleId;
    /**
    * 资源|id
    */
    @ApiModelProperty(value = "资源|id")
    private List<String> resourceId;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime editTime;


}