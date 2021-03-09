package com.halayang.server.user.controller;

import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.user.dto.RefreshTokenDto;
import com.halayang.server.user.dto.UserLoginDto;
import com.halayang.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/27 14:06           1.0
 * @program course-online
 * @description
 * @create 2021/2/27 14:06
 */
@Api(tags = {"oauth登录获取令牌"})
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(String code) {
        return code;
    }

    @ApiOperation(value = "登录获取令牌", httpMethod = "POST", notes = "登录获取令牌")
    @PostMapping("/login")
    public ResponseObject<Map<String, Object>> login(@ApiParam @RequestBody @Validated UserLoginDto userLoginDto) {
        return ResponseResult.success(userService.login(userLoginDto));
    }

    @ApiOperation(value = "刷新令牌", httpMethod = "POST", notes = "刷新令牌")
    @PostMapping("/refreshToken")
    public ResponseObject<Map<String, Object>> refreshToken(@ApiParam @RequestBody @Validated RefreshTokenDto refreshTokenDto) {
        return ResponseResult.success(userService.refreshToken(refreshTokenDto));
    }

}
