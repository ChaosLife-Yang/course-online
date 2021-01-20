package com.halayang.server.file.controller;

import com.halayang.server.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description oss上传文件
 * @author YangYuDi
 * @create 2021/1/19 20:41
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/1/19 20:41           1.0                         oss上传文件
 */
@RestController
@RequestMapping("/oss")
public class OssFileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(MultipartFile file){
        fileService.uploadToOss(file,null,null);
        return "ok";
    }

}
