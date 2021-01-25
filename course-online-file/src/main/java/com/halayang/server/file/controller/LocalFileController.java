package com.halayang.server.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.common.utils.JacksonUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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
@Slf4j
@RestController
@RequestMapping("/local")
public class LocalFileController {
    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.show}")
    private String showPath;

    @Autowired
    private FileService fileService;

    @PostMapping("/shardUpload")
    public ResponseObject<String> shardUpload(FileDTO fileDTO) {
        String relative = fileService.shardFileUpload(fileDTO);
        return ResponseResult.success(String.format("%s%s", showPath, relative));
    }

    @GetMapping("/check/{key}")
    public ResponseObject<Integer> checkKey(@PathVariable String key) {
        FilePO one = fileService.getOne(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, key));
        //判断上传到第几个分片了
        if (ObjectUtils.isEmpty(one)) {
            return ResponseResult.success(0);
        } else {
            return ResponseResult.success(one.getShardIndex());
        }
    }

    @PostMapping("/uploadCourseFile")
    public String uploadCourseFile(@RequestParam MultipartFile file) {
        String finalShowPath = fileService.uploadMultipartFile(file, filePath, FileUseEnum.COURSE);
        return showPath + finalShowPath;
    }

    @PostMapping("/uploadTeacherFile")
    public String uploadTeacherFile(@RequestParam MultipartFile file) {
        String finalShowPath = fileService.uploadMultipartFile(file, filePath, FileUseEnum.TEACHER);
        return showPath + finalShowPath;
    }

    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        String finalShowPath = fileService.uploadMultipartFile(file, filePath, FileUseEnum.COURSE);
        Map<String, String> map = new HashMap<>(2);
        map.put("link", showPath + finalShowPath);
        return map;
    }


}
