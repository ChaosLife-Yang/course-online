package com.halayang.server.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.config.executor.TaskExecutorConfig;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.mapper.FileMapper;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.FileService;
import com.halayang.server.file.util.AliyunConstants;
import com.halayang.server.file.util.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @Autowired
    private AliyunConstants aliyunConstants;

    @Value("${file.upload.path}")
    private String filePath;

    @Override
    public String shardFileUpload(FileDTO fileDTO) {
        int count = this.count(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, fileDTO.getFileKey()));
        if (count > 0) {
            //如果文件已经上传完成分片合并完成就可以极速秒传了
            FilePO one = this.getOne(new LambdaUpdateWrapper<FilePO>()
                    .eq(FilePO::getFileKey, fileDTO.getFileKey()));
            if (one.getShardIndex() == one.getShardTotal() - 1) {
                return one.getPath();
            }
        }
        //保存文件
        String saveName = PathUtils.saveMultipartFile(fileDTO, filePath);
        FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
        filePo.setPath(saveName);
        //不存在就插入，存在就更新
        if (count > 0) {
            this.update(new LambdaUpdateWrapper<FilePO>()
                    .set(FilePO::getShardIndex, fileDTO.getShardIndex())
                    .eq(FilePO::getFileKey, fileDTO.getFileKey()));
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

            for (int i = 0; i < shardTotal; i++) {
                String path = filePath + relativePath + "." + i;
                File file = new File(path);
                boolean result = file.delete();
                log.info("删除{} {}", path, result ? "成功" : "失败");
            }

        }

    }

    @Override
    public String uploadMultipartFile(MultipartFile file, String filePath, FileUseEnum fileUseEnum) {
        FileDTO fileDTO = PathUtils.saveMultipartFile(file, filePath);
        fileDTO.setUseTo(fileUseEnum.getCode());
        String finalShowPath = fileDTO.getPath();
        FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
        this.save(filePo);
        return finalShowPath;
    }

    @Override
    public void uploadToOss(MultipartFile file, String filePath, FileUseEnum fileUseEnum) {
        try {
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = aliyunConstants.getEndpoint();
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
            String accessKeyId = aliyunConstants.getKeyId();
            String accessKeySecret = aliyunConstants.getKeySecret();
            String bucketName = aliyunConstants.getBucketName();
            // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            String objectName = PathUtils.getSaveFileName(file);

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 创建InitiateMultipartUploadRequest对象。
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);

            // 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            request.setObjectMetadata(metadata);

            // 初始化分片。
            InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
            // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
            String uploadId = upresult.getUploadId();
            log.info("分片上传事件id =>{}", uploadId);
            // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
            List<PartETag> partETags = new CopyOnWriteArrayList<>();
            // 计算文件有多少个分片。
            // 一个分片10MB
            long partSize = 10 * 1024 * 1024L;

            long fileLength = file.getSize();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            // 遍历分片上传。
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                //获取文件流
                InputStream inputStream = file.getInputStream();
                // 跳过已经上传的分片。
                inputStream.skip(startPos);
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(objectName);
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(inputStream);
                // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
                uploadPartRequest.setPartSize(curPartSize);
                // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
                uploadPartRequest.setPartNumber(i + 1);
                // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
                partETags.add(uploadPartResult.getPartETag());
                log.info("分片上传完成 {}", uploadPartResult.getPartETag().getETag());
            }

            // 创建CompleteMultipartUploadRequest对象。
            // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
            CompleteMultipartUploadRequest completeMultipartUploadRequest =
                    new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

            // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
            completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);
            // 完成上传。
            CompleteMultipartUploadResult result = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
            log.info("上传完毕 {}", result.getLocation());
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            log.error("上传文件至OSS失败", e);
        }
    }


}
