package com.halayang.server.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.mapper.FileMapper;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-12
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FilePO> implements FileService {

    @Override
    public String uploadMultipartFile(MultipartFile file, String filePath, FileUseEnum fileUseEnum) {
        FileDTO fileDTO = FileUtils.saveMultipartFile(file, filePath);
        fileDTO.setUseTo(fileUseEnum.getCode());
        String finalShowPath = fileDTO.getPath();
        FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
        this.save(filePo);
        return finalShowPath;
    }
}
