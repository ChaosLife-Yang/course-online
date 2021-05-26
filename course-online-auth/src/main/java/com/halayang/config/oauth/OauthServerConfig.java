package com.halayang.config.oauth;

import com.halayang.config.exception.OauthExceptionEntryPoint;
import com.halayang.config.handler.OauthAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用jwt作为令牌，使用jdbc存储客户端信息
 *
 * @author YUDI
 * @date 2020/8/19
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 数据库连接对象
     */
    private final DataSource dataSource;

    private final UserDetailsService userDetailsService;
    /**
     * 授权码模式专用对象
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 签名
     */
    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 令牌保存策略 采用jwt令牌
     */
    private final TokenStore tokenStore;

    /**
     * jwt令牌扩展信息
     */
    private final JwtTokenStoreConfig.JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 认证异常
     */
    private final OauthExceptionEntryPoint oauthExceptionEntryPoint;

    /**
     * 自定义403返回
     */
    private final OauthAccessDeniedHandler accessDeniedHandler;

    public OauthServerConfig(DataSource dataSource,
                             @Qualifier("myUserDetailsService") UserDetailsService userDetailsService,
                             AuthenticationManager authenticationManager,
                             OauthAccessDeniedHandler accessDeniedHandler,
                             OauthExceptionEntryPoint oauthExceptionEntryPoint,
                             JwtAccessTokenConverter jwtAccessTokenConverter,
                             TokenStore tokenStore,
                             JwtTokenStoreConfig.JwtTokenEnhancer jwtTokenEnhancer) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.accessDeniedHandler = accessDeniedHandler;
        this.oauthExceptionEntryPoint = oauthExceptionEntryPoint;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenStore = tokenStore;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    /**
     * 客户端信息来源
     *
     * @return
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 客户信息配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                //对获取token的请求不进行拦截
                .tokenKeyAccess("permitAll()")
                //检查token的策略
                .checkTokenAccess("isAuthenticated()")
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(oauthExceptionEntryPoint);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // jwt token 增强链
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);

        endpoints.userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                //如果使用jwt就需要配置这个
                .tokenEnhancer(enhancerChain);
    }

}
