package com.halayang.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/5 20:27           1.0                         认证鉴权接口调用
 * @program course-online
 * @description feign请求携带token
 * @create 2021/3/5 20:27
 */
@Slf4j
@Configuration
public class Oauth2FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 重写apply方法
     *
     * @param requestTemplate requestTemplate对象
     * @author WangMingshuai
     * @date 2020/08/08 15:13
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", "Bearer", details.getTokenValue()));
        }
    }
}
