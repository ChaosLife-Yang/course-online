package com.halayang.server.file.controller;

import com.halayang.server.file.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 10:12           1.0                         文件
 * @program course-online
 * @description 上传至本地的文件
 * @create 2021/1/12 10:12
 */
@RestController
public class LocalFileController {
    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.show}")
    private String showPath;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String postName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String parentPath = FileUtils.getTimePath();
        //最终文件名
        String finalName = UUID.randomUUID().toString() + postName;
        String finalPath = filePath + parentPath + finalName;
        File dest = new File(finalPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new IllegalArgumentException("IO出错");
        }
        String finalShowPath = parentPath + finalName;
        return showPath + finalShowPath;
    }

    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String postName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String parentPath = FileUtils.getTimePath();
        //最终文件名
        String finalName = UUID.randomUUID().toString() + postName;
        String finalPath = filePath + parentPath + finalName;
        File dest = new File(finalPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new IllegalArgumentException("IO出错");
        }
        String finalShowPath = parentPath + finalName;
        Map<String, String> map = new HashMap<>(2);
        map.put("link", showPath + finalShowPath);
        return map;
    }


}
