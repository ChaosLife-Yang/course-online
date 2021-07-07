package com.halayang.feign;

import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.feign.factory.AuthFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/5 20:27           1.0                         认证鉴权接口调用
 * @program course-online
 * @description 认证鉴权接口调用
 * @create 2021/3/5 20:27
 */
@FeignClient(name = "yudi-auth", contextId = "authFeign", fallbackFactory = AuthFeignFallbackFactory.class)
@Component("authFeign")
public interface AuthFeign {

    /**
     * 获取用户资源
     *
     * @param id 用户id
     * @return com.halayang.common.utils.response.ResponseObject<java.util.List < java.lang.String>>
     * @author YangYudi
     * @date 2021/3/5 20:33
     */
    @GetMapping("/resource/userResources/{id}")
    ResponseObject<List<String>> getUserResources(@PathVariable("id") String id);

    /**
     * 添加oauth2被管理客户端信息
     *
     * @param clientDetail oauth2客户端信息dto
     * @return void
     * @author YangYudi
     * @date 2021/2/26 13:49
     */
    @PostMapping("/client")
    ResponseObject<String> addClientDetail(@RequestBody OauthClientDetailsDTO clientDetail);
}
