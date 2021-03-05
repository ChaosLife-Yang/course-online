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
 * @create 2021/2/9 14:31
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/2/9 14:31           1.0                         
 */
@ApiModel(value = "UserLoginDto对象", description = "用户登录")
@Data
public class UserLoginDto {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不为空")
    private String password;

    @ApiModelProperty(value = "客户端Id")
    @NotBlank(message = "客户端Id不为空")
    private String clientId;

    @ApiModelProperty(value = "客户端秘钥")
    @NotBlank(message = "客户端秘钥不为空")
    private String clientSecret;

}
