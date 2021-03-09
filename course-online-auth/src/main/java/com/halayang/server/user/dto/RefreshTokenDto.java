package com.halayang.server.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program oauth2-authority
 * @description 
 * @author YangYuDi
 * @create 2021/2/9 14:44
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/2/9 14:44           1.0                         
 */
@ApiModel(value = "RefreshTokenDto对象", description = "刷新令牌请求对象")
@Data
public class RefreshTokenDto {

    @ApiModelProperty(value = "刷新令牌", required = true, example = "test")
    @NotBlank(message = "刷新令牌不为空")
    private String token;

    @ApiModelProperty(value = "客户端Id", required = true, example = "client")
    @NotBlank(message = "客户端Id不为空")
    private String clientId;

    @ApiModelProperty(value = "客户端秘钥", required = true, example = "123456")
    @NotBlank(message = "客户端秘钥不为空")
    private String clientSecret;

}
