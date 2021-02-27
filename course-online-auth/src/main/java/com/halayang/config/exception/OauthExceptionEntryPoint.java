package com.halayang.config.exception;

import com.halayang.common.utils.JacksonUtils;
import com.halayang.common.utils.response.ResponseCode;
import com.halayang.common.utils.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/8 9:14           1.0
 * @program oauth2-authority
 * @description
 * @create 2021/2/8 9:14
 */
@Primary
@Configuration
@Slf4j
public class OauthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();

        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (cause instanceof InvalidTokenException) {
            String msg = JacksonUtils.toString(ResponseResult.error(ResponseCode.UNAUTHORIZED.code(), "无效token"));
            response.getWriter().write(msg);
        } else {
            String msg = JacksonUtils.toString(ResponseResult.error(ResponseCode.UNAUTHORIZED.code(), "认证失败"));
            response.getWriter().write(msg);
        }
    }

}

