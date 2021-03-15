package com.halayang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/13 19:13           1.0
 * @program course-online
 * @description
 * @create 2021/3/13 19:13
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.halayang.server.*.mapper")
@SpringBootApplication
public class CourseOnlineFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseOnlineFrontApplication.class, args);
    }

}
