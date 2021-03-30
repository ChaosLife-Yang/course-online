package com.halayang.config.handler;

import com.halayang.common.exception.BusinessException;
import com.halayang.common.utils.ContextHolder;
import com.halayang.common.utils.JwtUtils;
import com.halayang.common.utils.response.ResponseCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description 
 * @author YangYuDi
 * @create 2021/3/30 13:32
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/3/30 13:32           1.0                        根据token拦截登录状态
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        //校验请求头中的令牌格式
        boolean bearer = authorization.startsWith("Bearer ");
        if (!bearer){
            throw new BusinessException(ResponseCode.UNAUTHORIZED);
        }
        String token = authorization.substring(7);
        //校验令牌
        boolean checkToken = JwtUtils.checkToken(token);
        if (!checkToken) {
            throw new BusinessException(ResponseCode.UNAUTHORIZED);
        }
        ContextHolder.setUserId(JwtUtils.getMemberIdByJwtToken(token));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHolder.remove();
    }
}
