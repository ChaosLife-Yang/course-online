# 在线课程平台

## 项目结构说明
基于spring cloud vue的前后端分离架构
```
|——course-online-common    		通用模块
|——course-online-core 			核心模块
|——course-online-auth 			权限管理
|——course-online-file 			文件模块
|——course-online-gateway		网关模块
|——course-online-ui    	        前端工程
   |——course-online-ui-admin    后台管理页面
   |——course-online-ui-web      前台页面
|——generator                    代码生成器
|——nacos-1.3.0                  项目所用nacos,内含一些配置文件,没有上传至git

```



## 技术体系

使用 Spring Boot2.2.4.RELEASE 
整合 Spring Cloud Hoxton.RELEASE, Spring-Cloud-Alibaba 2.2.0.RELEASE,
mybatis-plus 3.3.1,spring security oauth2.0 ,使用jwt作为令牌

## Nacos配置中心的配置文件
有一些配置文件没有直接写在各个微服务的yml配置文件中，而是放在nacos中，在此列出来

jackson-dev.yml

```yaml
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    serialization:
      indent_output: false
      fail_on_empty_beans: true
    defaultPropertyInclusion: NON_EMPTY
    deserialization:
      fail_on_unknown_properties: false
    parser:
      allow_unquoted_control_chars: true
      allow_single_quotes: true
```

aliyun.yml

阿里云账号的一些信息就不暴露了

```yaml
# 阿里云
aliyun:
  file:
    endpoint: oss-cn-hangzhou.aliyuncs.com
    keyid: xxxxxx
    keysecret: xxxx
    bucketname: xxxx
    show: https://xxx.oss-cn-hangzhou.aliyuncs.com/
```

redis-dev.yml

云服务器ip就不暴露了

```yaml
spring:
  redis:
    host: xxx.xxx.xx.xx
    port: 6379
    password: 1234
    timeout: 10000
    lettuce:
      pool:
        max-active: 8
        max-wait: -1ms
        min-idle: 0
        max-idle: 8
```

