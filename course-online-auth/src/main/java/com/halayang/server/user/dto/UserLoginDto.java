package com.halayang.server.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/9 14:31           1.0
 * @program oauth2-authority
 * @description
 * @create 2021/2/9 14:31
 */
@ApiModel(value = "UserLoginDto对象", description = "用户登录")
@Data
public class UserLoginDto {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    @NotBlank(message = "用户名不为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotBlank(message = "密码不为空")
    private String password;

    @ApiModelProperty(value = "客户端Id", required = true, example = "client")
    @NotBlank(message = "客户端Id不为空")
    private String clientId;

    @ApiModelProperty(value = "客户端秘钥", required = true, example = "123456")
    @NotBlank(message = "客户端秘钥不为空")
    private String clientSecret;

}
