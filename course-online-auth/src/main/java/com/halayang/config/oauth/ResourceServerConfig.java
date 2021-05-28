package com.halayang.config.oauth;

import com.halayang.config.exception.OauthExceptionEntryPoint;
import com.halayang.config.handler.OauthAccessDeniedHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

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

    private final ApplicationContext applicationContext;

    public ResourceServerConfig(OauthAccessDeniedHandler accessDeniedHandler,
                                ApplicationContext applicationContext,
                                OauthExceptionEntryPoint oauthExceptionEntryPoint) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.applicationContext = applicationContext;
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
                .expressionHandler(oAuth2WebSecurityExpressionHandler(applicationContext))
                .stateless(true);
    }

    /**
     * 表达式处理程序 不然配置access会报错
     *
     * @param applicationContext 上下文对象
     * @return
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler securityExpressionHandler = new OAuth2WebSecurityExpressionHandler();
        securityExpressionHandler.setApplicationContext(applicationContext);
        return securityExpressionHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 过滤不需要认证的资源
        http.authorizeRequests()
                .antMatchers("/login", "/refreshToken", "/callback", "/oauth/**", "/client",
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/actuator", "/actuator/**")
                .permitAll()
                .antMatchers("/resource/userResources/**", "/role/userRoles/**").authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(oauthExceptionEntryPoint)
                .and()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();
    }

}
