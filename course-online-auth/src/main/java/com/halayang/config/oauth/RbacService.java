package com.halayang.config.oauth;

import com.halayang.common.utils.JacksonUtils;
import com.halayang.server.resource.dto.ResourceAuthorityDTO;
import com.halayang.server.resource.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ResourceService resourceService;

    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        try {
            if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
                //解析restful网址
                handlerMapping.getHandler(request);
                String urlPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

                //解析jwt
                Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
                if (details instanceof OAuth2AuthenticationDetails) {
                    OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
                    String token = oAuth2AuthenticationDetails.getTokenValue();
                    Jwt jwt = JwtHelper.decode(token);
                    Map<String, Object> map = JacksonUtils.toMap(jwt.getClaims(), String.class, Object.class);
                    String userId = (String) map.get("user_id");
                    log.info("用户id:{},要访问的restful url:{}", userId, urlPattern);
                    if (!StringUtils.isEmpty(userId)) {
                        //查询用户权限能否访问此url
                        List<ResourceAuthorityDTO> permission = resourceService.getPermissionByUserId(userId);
                        List<String> collect = getPermissionUrls(permission);
                        log.info("用户能访问的url:{}", collect);
                        return collect.contains(urlPattern);
                    }
                }
            }
            return false;
        } catch (Exception e) {
            log.error("认证失败", e);
            return false;
        }
    }

    public static List<String> getPermissionUrls(List<ResourceAuthorityDTO> permission) {
        return permission.stream()
                .filter(per -> !StringUtils.isEmpty(per.getRequest()))
                .map(ResourceAuthorityDTO::getRequest)
                .map(re -> JacksonUtils.toList(re, String.class))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }


}
