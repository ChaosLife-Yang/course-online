package com.halayang.server.file.controller;

import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.FileMessageDTO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.util.FileIOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"上传到本地的文件接口"})
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

    @ApiOperation(value = "获取文件MD5值", httpMethod = "POST", notes = "获取文件MD5值")
    @PostMapping("/getMd5")
    public ResponseObject<String> getMd5(@RequestParam MultipartFile file) {
        try {
            String fileMd5 = FileIOUtils.getFileMd5(file.getInputStream());
            return ResponseResult.success(fileMd5);
        } catch (IOException e) {
            throw new IllegalArgumentException("获取md5失败");
        }
    }

    @ApiOperation(value = "分片上传", httpMethod = "POST", notes = "分片上传")
    @PostMapping("/shardUpload")
    public ResponseObject<String> shardUpload(FileDTO fileDTO) {
        String relative = fileService.shardFileUpload(fileDTO);
        return ResponseResult.success(String.format("%s%s", showPath, relative));
    }

    @ApiOperation(value = "根据文件MD5获取文件信息", httpMethod = "POST", notes = "根据文件MD5获取文件信息")
    @GetMapping("/check/{key}")
    public ResponseObject<FileMessageDTO> checkKey(@PathVariable String key) {
        return ResponseResult.success(fileService.getShardIndex(key));
    }

    @ApiOperation(value = "富文本编辑器上传图片", httpMethod = "POST", notes = "富文本编辑器上传图片")
    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        String finalShowPath = fileService.courseContentUpload(file);
        Map<String, String> map = new HashMap<>(2);
        map.put("link", showPath + finalShowPath);
        return map;
    }


}
