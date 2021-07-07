package com.halayang.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halayang.common.utils.response.ResponseCode;
import com.halayang.common.utils.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
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
    /**
     * jackson mapper
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * token 超时
     */
    private static final String ACCESS_TOKEN_EXPIRED = "Access token expired";

    /**
     * token 转换JSON失败
     */
    private static final String CONVERT_ACCESS_FAILED = "Cannot convert access token to JSON";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        log.error("认证失败 {}", e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // token 超时
        if (e.getMessage().contains(ACCESS_TOKEN_EXPIRED)) {
            OBJECT_MAPPER.writeValue(response.getOutputStream(),
                    ResponseResult.error(ResponseCode.UNAUTHORIZED_TOKEN.code(), ResponseCode.UNAUTHORIZED_TOKEN.message()));
        }
        // token 转换JSON失败
        else if (e.getMessage().contains(CONVERT_ACCESS_FAILED)) {
            OBJECT_MAPPER.writeValue(response.getOutputStream(),
                    ResponseResult.error(ResponseCode.INVALID_TOKEN.code(), ResponseCode.INVALID_TOKEN.message()));
        } else {
            OBJECT_MAPPER.writeValue(response.getOutputStream(),
                    ResponseResult.error(ResponseCode.UNAUTHORIZED.code(), ResponseCode.UNAUTHORIZED.message()));
        }
    }

}

