package com.halayang.server.user.dto;

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
@Data
public class RefreshTokenDto {

    @NotBlank(message = "刷新令牌不为空")
    private String token;

    @NotBlank(message = "客户端Id不为空")
    private String clientId;

    @NotBlank(message = "客户端秘钥不为空")
    private String clientSecret;

}
