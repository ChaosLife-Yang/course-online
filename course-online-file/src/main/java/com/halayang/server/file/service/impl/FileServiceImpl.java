package com.halayang.server.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.FileMessageDTO;
import com.halayang.server.file.mapper.FileMapper;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.util.FileIOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <p>
 * 文件 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-12
 */
@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FilePO> implements FileService {

    @Value("${file.upload.path}")
    private String filePath;

    @Override
    public FileMessageDTO getShardIndex(String key) {
        FilePO one = this.getOne(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, key));
        FileMessageDTO fileMessageDTO;
        //判断上传到第几个分片了 数据库找不到就表示没上传过 分片索引为0
        //找到了但分片索引 分片总数等信息为null表示是文本编辑器上传的分片索引也为0
        //path为空表示是上传到vod了
        if (ObjectUtils.isEmpty(one) || StringUtils.isEmpty(one.getPath())) {
            fileMessageDTO = new FileMessageDTO();
        } else {
            if (StringUtils.isEmpty(one.getShardIndex()) && StringUtils.isEmpty(one.getShardTotal())) {
                fileMessageDTO = new FileMessageDTO()
                        .setShardIndex(0);
                return fileMessageDTO;
            }
            String path = one.getPath();
            String newName = path.substring(path.lastIndexOf("/") + 1);
            fileMessageDTO = new FileMessageDTO()
                    .setShardIndex(one.getShardIndex())
                    .setNewName(newName);
        }
        return fileMessageDTO;
    }

    @Override
    public String shardFileUpload(FileDTO fileDTO) {
        int count = this.count(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, fileDTO.getFileKey()));
        FilePO one = null;
        if (count > 0) {
            //如果文件已经上传完成分片合并完成就可以极速秒传了
            one = this.getOne(new LambdaUpdateWrapper<FilePO>()
                    .eq(FilePO::getFileKey, fileDTO.getFileKey()));
            if ((one.getShardIndex() == one.getShardTotal() - 1) && !StringUtils.isEmpty(one.getPath())) {
                return one.getPath();
            }
        }
        //保存文件
        String saveName = FileIOUtils.saveMultipartFile(fileDTO, filePath);
        FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
        filePo.setPath(saveName);
        //不存在就插入，存在就更新
        if (one != null) {
            //根据id上传不修改文件名
            filePo.setId(one.getId());
            filePo.setName(one.getName());
            this.updateById(filePo);
        } else {
            this.save(filePo);
        }
        if (fileDTO.getShardIndex() == fileDTO.getShardTotal() - 1) {
            fileDTO.setPath(saveName);
            //分片上传完毕可以合并了
            mergeFile(fileDTO);
        }
        return saveName;
    }

    /**
     * 分片合并
     *
     * @param fileDTO dto对象
     * @return void
     * @author YangYudi
     * @date 2021/1/24 17:31
     */
    private void mergeFile(FileDTO fileDTO) {
        String relativePath = fileDTO.getPath();
        Integer shardTotal = fileDTO.getShardTotal();
        String savePath = filePath + relativePath;
        //文件追加写入
        File newFile = new File(savePath);
        FileOutputStream outputStream = null;
        //分片文件
        FileInputStream fileInputStream = null;
        try {
            outputStream = new FileOutputStream(newFile, true);
            byte[] byt = new byte[10 * 1024 * 1024];
            int len;
            for (int i = 0; i < shardTotal; i++) {
                // 读取第i个分片
                fileInputStream = new FileInputStream(new File(savePath + "." + i));
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);
                }
            }
        } catch (IOException e) {
            log.error("合并分片异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                System.gc();
                Thread.sleep(100);
            } catch (Exception e) {
                log.error("IO流关闭异常", e);
            }
            //上传完毕 删除分片
            for (int i = 0; i < shardTotal; i++) {
                String path = filePath + relativePath + "." + i;
                File file = new File(path);
                boolean result = file.delete();
                log.info("删除分片{} {}", path, result ? "成功" : "失败");
            }
        }
    }

    @Override
    public String courseContentUpload(MultipartFile file) {
        try {
            //文件存在且上传完成直接返回地址
            String fileMd5 = FileIOUtils.getFileMd5(file.getInputStream());
            FilePO one = this.getOne(new LambdaUpdateWrapper<FilePO>()
                    .eq(FilePO::getFileKey, fileMd5));
            if (!ObjectUtils.isEmpty(one) && (one.getShardIndex() == one.getShardTotal() - 1) && !StringUtils.isEmpty(one.getPath())) {
                return one.getPath();
            }
            //不存在就上传
            FileDTO fileDTO = FileIOUtils.saveMultipartFile(file, filePath);
            //通过富文本编辑器上传就默认只有一个分片
            fileDTO.setShardSize(fileDTO.getSize())
                    .setShardTotal(1)
                    .setShardIndex(0);
            FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
            filePo.setUseTo(FileUseEnum.COURSE.getCode())
                    .setFileKey(fileMd5);
            this.save(filePo);
            return filePo.getPath();
        } catch (IOException e) {
            log.error("上传文件有误", e);
            throw new IllegalArgumentException("上传文件有误");
        }
    }


}
