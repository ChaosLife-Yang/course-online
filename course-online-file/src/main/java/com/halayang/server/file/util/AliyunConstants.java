package com.halayang.server.file.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/19 20:46           1.0
 * @program course-online
 * @description
 * @create 2021/1/19 20:46
 */
@Component
@Data
public class AliyunConstants {

    /**
     * 读取配置文件内容
     */
    @Value("${aliyun.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.file.keyid}")
    private String keyId;

    @Value("${aliyun.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.file.bucketname}")
    private String bucketName;

}
