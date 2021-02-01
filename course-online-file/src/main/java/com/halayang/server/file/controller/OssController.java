package com.halayang.server.file.controller;

import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.VideoVodDTO;
import com.halayang.server.file.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * @program course-online
 * @description
 * @create 2021/1/26 10:11
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/26 10:11           1.0
 */
@Api(tags = {"上传到OSS的文件接口"})
@Slf4j
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "分片上传到oss", httpMethod = "POST", notes = "分片上传")
    @PostMapping("/upload")
    public ResponseObject<String> uploadOss(FileDTO fileDTO) {
        return ResponseResult.success(ossService.upload(fileDTO));
    }

    @ApiOperation(value = "富文本编辑器上传图片", httpMethod = "POST", notes = "分片上传")
    @PostMapping("/contentUpload")
    public Map<String, String> contentUpload(@RequestParam MultipartFile file) {
        Map<String, String> map = new HashMap<>(2);
        map.put("link", ossService.contentUpload(file));
        return map;
    }

    @ApiOperation(value = "上传视频到阿里云视频点播", httpMethod = "POST", notes = "上传视频到阿里云视频点播")
    @PostMapping("/vod")
    public ResponseObject<VideoVodDTO> vod(FileDTO fileDTO) {
        return ResponseResult.success(ossService.getVodMessage(fileDTO));
    }

    @ApiOperation(value = "获取播放凭证", httpMethod = "POST", notes = "获取播放凭证")
    @PostMapping("/getPlayAuth")
    public ResponseObject<String> getPlayAuth(@RequestParam String vod){
        return ResponseResult.success(ossService.getVodAuth(vod));
    }

}
