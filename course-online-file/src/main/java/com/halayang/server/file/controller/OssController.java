package com.halayang.server.file.controller;

import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/26 10:11           1.0
 * @program course-online
 * @description
 * @create 2021/1/26 10:11
 */
@Slf4j
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public ResponseObject<String> uploadOss(FileDTO fileDTO) {
        return ResponseResult.success(ossService.upload(fileDTO));
    }

    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        Map<String, String> map = new HashMap<>(2);
        map.put("link", ossService.contentUpload(file));
        return map;
    }

}
