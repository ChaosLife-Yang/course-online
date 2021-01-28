package com.halayang.server.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.FileMessageDTO;
import com.halayang.server.file.po.FilePO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-12
 */
public interface FileService extends IService<FilePO> {

    FileMessageDTO getShardIndex(String key);

    String shardFileUpload(FileDTO fileDTO);

    String courseContentUpload(MultipartFile file);

}
