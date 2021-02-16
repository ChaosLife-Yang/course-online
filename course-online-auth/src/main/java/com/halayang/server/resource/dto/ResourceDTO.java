package com.halayang.server.resource.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * resourceDTO对象
 *
 * @author YangYuDi
 * @since 2021-02-16 13:48:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ResourceDTO对象", description = "resourceDTO数据交互对象")
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 父id
    */
    @ApiModelProperty(value = "父id")
    private String parent;
    /**
    * 名称|菜单或按钮
    */
    @ApiModelProperty(value = "名称|菜单或按钮")
    private String name;
    /**
    * 权限标识
    */
    @ApiModelProperty(value = "权限标识")
    private String permissionValue;
    /**
    * 页面|路由
    */
    @ApiModelProperty(value = "页面|路由")
    private String page;
    /**
    * 请求|接口
    */
    @ApiModelProperty(value = "请求|接口")
    private String request;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime editTime;


}