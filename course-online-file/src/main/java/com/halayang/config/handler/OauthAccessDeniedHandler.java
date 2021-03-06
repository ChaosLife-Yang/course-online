package com.halayang.config.handler;

import com.halayang.common.utils.JacksonUtils;
import com.halayang.common.utils.response.ResponseCode;
import com.halayang.common.utils.response.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author yangyudi
 * @version 1.0.0
 * <author>         <time>                  <version>                   <description>
 * yangyudi         2020/11/15 15:35          1.0
 * @program Security_Study
 * @description 自定义403
 * @create 2020/11/15 15:35
 */
@Component
public class OauthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-type", "application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String msg = JacksonUtils.toString(ResponseResult.error(ResponseCode.FORBIDDEN.code(), ResponseCode.FORBIDDEN.message()));
        writer.write(msg);
        writer.flush();
        writer.close();
    }
}
