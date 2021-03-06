package com.halayang.feign.factory;

import com.halayang.feign.AuthFeign;
import com.halayang.feign.fallback.AuthFeignFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description 
 * @author YangYuDi
 * @create 2021/3/6 11:45
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/3/6 11:45           1.0                         
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
