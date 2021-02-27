package com.halayang.config.oauth;

import com.halayang.config.exception.OauthExceptionEntryPoint;
import com.halayang.config.handler.OauthAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 既是认证服务 也是资源服务
 *
 * @author YUDI
 * @date 2020/4/13
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "course-online";

    private final OauthExceptionEntryPoint oauthExceptionEntryPoint;

    private final OauthAccessDeniedHandler accessDeniedHandler;

    public ResourceServerConfig(OauthAccessDeniedHandler accessDeniedHandler,
                                OauthExceptionEntryPoint oauthExceptionEntryPoint) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.oauthExceptionEntryPoint = oauthExceptionEntryPoint;
    }

    /**
     * 指定当前资源id
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //指定当前资源的id，非常重要！必须写！
        resources.resourceId(RESOURCE_ID)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(oauthExceptionEntryPoint)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 过滤不需要认证的资源
        http.authorizeRequests()
                .antMatchers("/login", "/refreshToken", "/oauth/**",
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/actuator", "/actuator/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();
    }

}
