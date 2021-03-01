package com.halayang.config.oauth;

import com.halayang.server.user.po.UserPO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author yangyudi
 * @version 1.0.0
 * <author>         <time>                  <version>                   <description>
 * yangyudi         2020/11/15 16:00          1.0
 * @program Security_Study
 * @description
 * @create 2020/11/15 16:00
 */
@Configuration
public class JwtTokenStoreConfig {
    /**
     * 将access_token转换为jwt_token
     *
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置签名密钥
        jwtAccessTokenConverter.setSigningKey("yudi");
        return jwtAccessTokenConverter;
    }

    /**
     * 指定token持久化策略
     * InMemoryTokenStore 往内存中存  (大型项目不建议采用)
     * RedisTokenStore 往redis中存
     * JdbcTokenStore 往数据库中存
     * JwtTokenStore 使用jwt令牌
     *
     * @return org.springframework.security.oauth2.provider.token.store.JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    /**
     * jwt令牌扩展信息类
     */
    static class JwtTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            UserPO principal = (UserPO) authentication.getPrincipal();
            //添加jwt扩展信息
            Map<String, Object> map = new HashMap<>(2);
            map.put("user_id", principal.getId());
            map.put("name", principal.getName());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        }
    }

}
