package com.halayang.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 10:24           1.0
 * @program course-online
 * @description
 * @create 2021/1/12 10:24
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.show}")
    private String showPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] split = showPath.split("/");
        String s = split[split.length - 1];
        String pattern = new StringBuilder("/").append(s).append("/**").toString();
        //需要告知系统，这是要被当成静态文件的！
        registry.addResourceHandler(pattern).addResourceLocations("file:" + filePath);
    }

}
