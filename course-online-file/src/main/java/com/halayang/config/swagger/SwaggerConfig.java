package com.halayang.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author YangYudi
 * @date 2020/12/14 14:02
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    /**
     * 创建SwaggerApi
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author YangYudi
     * @date 2020/12/14 14:02
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.halayang.server"))
                .paths(PathSelectors.any())
                .build()
                // 配置权限
//                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    /**
     * Swagger API基本信息
     *
     * @return springfox.documentation.service.ApiInfo
     * @author YangYudi
     * @date 2020/12/14 14:02
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("yudi", "http://localhost:9000/doc.html", "529699377@qq.com");
        return new ApiInfoBuilder()
                .title("course-online-swagger-ui RESTFul APIs")
                .description("course-online-swagger-ui")
                .termsOfServiceUrl("http://localhost:9000/")
                .contact(contact)
                .version("1.0")
                .build();
    }

    /**
     * 配置token类型为Bearer
     *
     * @return springfox.documentation.service.ApiKey
     * @author YangYudi
     * @date 2020/12/14 14:02
     */
    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    /**
     * Security上下文设置
     *
     * @return springfox.documentation.spi.service.contexts.SecurityContext
     * @author YangYudi
     * @date 2020/12/14 14:02
     */
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/.*"))
//                .build();
//    }

    /**
     * 默认权限
     *
     * @return java.util.List<springfox.documentation.service.SecurityReference>
     * @author YangYudi
     * @date 2020/12/14 14:02
     */
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
//    }

}
