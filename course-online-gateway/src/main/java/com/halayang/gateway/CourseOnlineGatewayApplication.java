package com.halayang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>         <time>                  <version>                   <description>
 * YangYuDi         2020/11/28 15:27          1.0.0
 * @program course-online
 * @description
 * @create 2020/11/28 15:27
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CourseOnlineGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseOnlineGatewayApplication.class, args);
    }

}
