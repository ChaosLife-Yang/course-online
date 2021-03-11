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
