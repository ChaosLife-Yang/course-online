package com.halayang.server.resource.dto;

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

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "父id")
    private String parent;

    @ApiModelProperty(value = "名称|菜单或按钮")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String permissionValue;

    @ApiModelProperty(value = "页面|路由")
    private String page;

    @ApiModelProperty(value = "请求|接口")
    private String request;

    @ApiModelProperty(value = "子节点")
    private List<ResourceDTO> children;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime editTime;


}