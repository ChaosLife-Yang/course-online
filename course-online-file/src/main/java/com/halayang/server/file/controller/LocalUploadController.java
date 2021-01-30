package com.halayang.server.file.controller;

import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.FileMessageDTO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.util.FileIOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * @program course-online
 * @description 上传至本地的文件
 * @create 2021/1/12 10:12
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 10:12           1.0                         文件
 */
@Slf4j
@RestController
@RequestMapping("/local")
public class LocalUploadController {
    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.show}")
    private String showPath;

    @Autowired
    private FileService fileService;

    @PostMapping("/getMd5")
    public ResponseObject<String> getMd5(@RequestParam MultipartFile file) {
        try {
            String fileMd5 = FileIOUtils.getFileMd5(file.getInputStream());
            return ResponseResult.success(fileMd5);
        } catch (IOException e) {
            throw new IllegalArgumentException("获取md5失败");
        }
    }

    @PostMapping("/shardUpload")
    public ResponseObject<String> shardUpload(FileDTO fileDTO) {
        String relative = fileService.shardFileUpload(fileDTO);
        return ResponseResult.success(String.format("%s%s", showPath, relative));
    }

    @GetMapping("/check/{key}")
    public ResponseObject<FileMessageDTO> checkKey(@PathVariable String key) {
        return ResponseResult.success(fileService.getShardIndex(key));
    }

    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        String finalShowPath = fileService.courseContentUpload(file);
        Map<String, String> map = new HashMap<>(2);
        map.put("link", showPath + finalShowPath);
        return map;
    }


}
