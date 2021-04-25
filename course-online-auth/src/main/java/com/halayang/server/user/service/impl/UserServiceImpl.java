package com.halayang.server.user.service.impl;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.common.utils.JacksonUtils;
import com.halayang.common.utils.response.ResponseCode;
import com.halayang.server.user.dto.RefreshTokenDto;
import com.halayang.server.user.dto.UserLoginDto;
import com.halayang.server.user.mapper.UserMapper;
import com.halayang.server.user.po.UserPO;
import com.halayang.server.user.service.UserService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TOKEN_PREFIX = "token_";

    @Override
    public boolean saveOrUpdateUser(UserPO userPo) {
        if (StringUtils.isEmpty(userPo.getId())) {
            LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<UserPO>()
                    .eq(UserPO::getLoginName, userPo.getLoginName());
            int count = this.count(wrapper);
            if (count > 0) {
                throw new IllegalArgumentException("用户名已被注册，请换一个");
            }
            userPo.setPassword(BCryptUtils.encode("123456"));
            return this.save(userPo);
        }
        return this.updateById(userPo);
    }

    @Override
    public Map<String, Object> login(UserLoginDto userLoginDto) {
        try {
            //先要在redis中判断用户是否登录过，登录过就直接从redis里返回令牌
            Map<String, Object> tokenMap = (Map<String, Object>) redisTemplate.opsForValue().get(TOKEN_PREFIX + userLoginDto.getUsername());
            if (!ObjectUtils.isEmpty(tokenMap)) {
                return tokenMap;
            }
            HttpClientBuilder builder = HttpClients.custom()
                    //关闭自动处理重定向
                    .disableAutomaticRetries()
                    //利用LaxRedirectStrategy处理重定向问题
                    .setRedirectStrategy(new LaxRedirectStrategy());

            CloseableHttpClient client = builder.build();
            //获取当前url
            String url = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
            //请求授权码
            HttpGet httpGet = new HttpGet(String.format("%s/oauth/authorize?response_type=code&client_id=%s", url, userLoginDto.getClientId()));
            String basic = String.format("Basic %s", new String(Base64.getEncoder().encode((String.format("%s:%s", userLoginDto.getUsername(), userLoginDto.getPassword())).getBytes())));
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, basic);
            //获取结果
            CloseableHttpResponse response = client.execute(httpGet);
            String code = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (StringUtils.isEmpty(code)) {
                log.error("授权码请求失败");
                throw new IllegalArgumentException("登录失败");
            }
            Map<String, Object> paramMap = new HashMap<>(4);
            paramMap.put("client_id", userLoginDto.getClientId());
            paramMap.put("grant_type", "authorization_code");
            paramMap.put("scope", "app");
            paramMap.put("code", code);
            //登录完成可以把token存入redis username为key 申请令牌中的expires_in为过期时间
            Map<String, Object> map = getMap(url, paramMap, userLoginDto.getClientId(), userLoginDto.getClientSecret());
            String expiresIn = String.valueOf(map.get("expires_in"));
            redisTemplate.opsForValue().set(TOKEN_PREFIX + userLoginDto.getUsername(), map, Long.parseLong(expiresIn), TimeUnit.SECONDS);
            return map;
        } catch (IOException e) {
            log.error("令牌请求失败", e);
            throw new IllegalArgumentException("登录失败");
        }
    }

    @Override
    public Map<String, Object> refreshToken(RefreshTokenDto refreshTokenDto) {
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("grant_type", "refresh_token");
        paramMap.put("refresh_token", refreshTokenDto.getToken());
        //获取当前url
        String url = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
        //请求令牌
        return getMap(url, paramMap, refreshTokenDto.getClientId(), refreshTokenDto.getClientSecret());
    }

    private Map<String, Object> getMap(String url, Map<String, Object> paramMap, String clientId, String clientSecret) {
        //请求令牌
        String tokenStr = HttpRequest.post(String.format("%s/oauth/token", url))
                .basicAuth(clientId, clientSecret)
                .form(paramMap)
                .execute()
                .body();
        Map<String, Object> map = JacksonUtils.toMap(tokenStr, String.class, Object.class);
        String status = "code";
        String access = "access_token";
        //key要存在 code要等于200
        Assert.notEmpty(map, "请求失败");
        boolean contain = map.containsKey(access) || (!ObjectUtils.isEmpty(map.get(status)) &&
                Integer.parseInt(String.valueOf(map.get(status))) == ResponseCode.REQUEST_SUCCESS.code());
        if (!contain) {
            throw new IllegalArgumentException("令牌请求失败");
        }
        return map;
    }
}
