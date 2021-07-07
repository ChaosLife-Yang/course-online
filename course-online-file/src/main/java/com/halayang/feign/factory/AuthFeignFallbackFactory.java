package com.halayang.feign.factory;

import com.halayang.feign.AuthFeign;
import com.halayang.feign.fallback.AuthFeignFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/6 11:45           1.0
 * @program course-online
 * @description
 * @create 2021/3/6 11:45
 */
@Component
@Slf4j
public class AuthFeignFallbackFactory implements FallbackFactory<AuthFeign> {

    private final AuthFeignFallback authFeignFallback;

    public AuthFeignFallbackFactory(AuthFeignFallback authFeignFallback) {
        this.authFeignFallback = authFeignFallback;
    }

    @Override
    public AuthFeign create(Throwable throwable) {
        log.error(throwable.getMessage());
        return authFeignFallback;
    }
}
