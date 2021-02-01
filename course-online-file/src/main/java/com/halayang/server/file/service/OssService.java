package com.halayang.server.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.VideoVodDTO;
import com.halayang.server.file.po.FilePO;
import org.springframework.web.multipart.MultipartFile;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/26 10:10           1.0
 * @program course-online
 * @description
 * @create 2021/1/26 10:10
 */
public interface OssService extends IService<FilePO> {

    /**
     * 分片上传到oss
     *
     * @param fileDTO 上传文件的表单内容
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/2/1 10:12
     */
    String upload(FileDTO fileDTO);

    /**
     * 富文本编辑器上传图片到oss
     *
     * @param file 图片文件
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/2/1 10:12
     */
    String contentUpload(MultipartFile file);

    /**
     * 获取vod文件信息
     *
     * @param fileDTO 上传文件的表单内容
     * @return com.halayang.server.file.dto.VideoVodDTO
     * @author YangYudi
     * @date 2021/2/1 10:12
     */
    VideoVodDTO getVodMessage(FileDTO fileDTO);

    /**
     * 根据vod获取播放凭证
     *
     * @param vod vod
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/2/1 10:13
     */
    String getVodAuth(String vod);

}
