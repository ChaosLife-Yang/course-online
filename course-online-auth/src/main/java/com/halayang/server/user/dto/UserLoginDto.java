package com.halayang.server.user.dto;

import lombok.Data;

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
@Data
public class UserLoginDto {

    private String username;

    private String password;

    private String clientId;

    private String clientSecret;

}
