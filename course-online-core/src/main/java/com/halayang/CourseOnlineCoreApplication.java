package com.halayang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>         <time>                  <version>                   <description>
 * YangYuDi         2020/11/28 15:03          1.0.0
 * @program course-online
 * @description
 * @create 2020/11/28 15:03
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.halayang.server.*.mapper")
public class CourseOnlineCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseOnlineCoreApplication.class, args);
    }

}
