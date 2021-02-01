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

    /**
     * 根据MD5获取文件信息
     *
     * @param key MD5值
     * @author YangYudi
     * @date 2021/2/1 10:10
     * @return com.halayang.server.file.dto.FileMessageDTO
     */
    FileMessageDTO getShardIndex(String key);

    /**
     * 分片上传
     *
     * @param fileDTO 上传文件的表单
     * @author YangYudi
     * @date 2021/2/1 10:11
     * @return java.lang.String
     */
    String shardFileUpload(FileDTO fileDTO);

    /**
     * 富文本编辑器上传
     *
     * @param file 文件
     * @author YangYudi
     * @date 2021/2/1 10:11
     * @return java.lang.String
     */
    String courseContentUpload(MultipartFile file);

}
