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

    String upload(FileDTO fileDTO);

    String contentUpload(MultipartFile file);

    VideoVodDTO getVodMessage(FileDTO fileDTO);

    String getVodAuth(String vod);

}
