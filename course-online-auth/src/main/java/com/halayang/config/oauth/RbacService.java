package com.halayang.config.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/9 14:09           1.0
 * @program oauth2-authority
 * @description
 * @create 2021/2/9 14:09
 */
@Component
@Slf4j
public class RbacService {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        try {
            if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
                //解析restful网址
                handlerMapping.getHandler(request);
                String urlPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
                log.info("restful url:{}", urlPattern);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
