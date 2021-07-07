package com.halayang.config.oauth;

import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.feign.AuthFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * copyright (C), 2019,
 *
 * @author WangMingShuai
 * @version 2.0.0
 * <author>                <time>                  <version>                   <description>
 * Yangyudi         2019/12/20 15:27             2.0.0                         系统启动完成监听
 * @program istorm-automation-core
 * 系统启动完成监听
 * @create 2019/12/20 15:27
 */
@Component
@Slf4j
public class ApplicationStartRegisterService implements ApplicationRunner {

    /**
     * oauth2 资源端注册参数
     */
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.resource-ids}")
    private String resourceIds;
    @Value("${security.address}")
    private String address;

    /**
     * 注册oauth service
     */
    private final AuthFeign authFeign;

    public ApplicationStartRegisterService(AuthFeign authFeign) {
        this.authFeign = authFeign;
    }

    /**
     * 项目启动后将服务注册到 oauth-server
     *
     * @param args ApplicationArguments对象
     * @return void
     * @author WangMingShuai
     * @date 2019/12/25 9:28
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{}", "项目启动完成，将服务注册到 oauth2 server");
        OauthClientDetailsDTO clientDetailDTO = OauthClientDetailsDTO.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resourceIds(resourceIds)
                .scope("app")
                .authorizedGrantTypes("authorization_code,refresh_token,password")
                .webServerRedirectUri(address + "/callback")
                .accessTokenValidity(86400)
                .autoapprove("true")
                .build();
        authFeign.addClientDetail(clientDetailDTO);
    }
}
