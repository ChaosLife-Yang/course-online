package com.halayang.server.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.server.file.dto.FileDTO;
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

    String shardFileUpload(FileDTO fileDTO);

    String uploadMultipartFile(MultipartFile file, String filePath, FileUseEnum fileUseEnum);

    void uploadToOss(MultipartFile file, String filePath, FileUseEnum fileUseEnum);


}
