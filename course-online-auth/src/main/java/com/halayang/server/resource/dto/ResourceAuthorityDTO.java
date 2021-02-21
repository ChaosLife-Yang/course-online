package com.halayang.server.resource.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/20 17:12           1.0
 * @program course-online
 * @description
 * @create 2021/2/20 17:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResourceAuthorityDTO {

    @ApiModelProperty(value = "名称|菜单或按钮")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String permissionValue;

    @ApiModelProperty(value = "请求|接口")
    private String request;

}