package com.halayang.server.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.enums.FileUseEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.file.dto.FileDTO;
import com.halayang.server.file.dto.VideoVodDTO;
import com.halayang.server.file.mapper.FileMapper;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.OssService;
import com.halayang.server.file.util.AliyunConstants;
import com.halayang.server.file.util.FileIOUtils;
import com.halayang.server.file.util.VodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/26 10:12           1.0
 * @program course-online
 * @description
 * @create 2021/1/26 10:12
 */
@Slf4j
@Service
public class OssServiceImpl extends ServiceImpl<FileMapper, FilePO> implements OssService {

    @Autowired
    private AliyunConstants aliyunConstants;

    @Override
    public String upload(FileDTO fileDTO) {
        int count = this.count(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, fileDTO.getFileKey()));
        FilePO one = null;
        if (count > 0) {
            //如果文件已经上传完成分片合并完成就可以极速秒传了
            LambdaQueryWrapper<FilePO> eq = new LambdaQueryWrapper<FilePO>()
                    .eq(FilePO::getFileKey, fileDTO.getFileKey());
            one = this.getOne(eq);
            String path = aliyunConstants.getShow();
            if ((one.getShardIndex() == one.getShardTotal() - 1) && !StringUtils.isEmpty(one.getPath())) {
                return path + one.getPath();
            }

        }
        try {
            MultipartFile file = fileDTO.getFile();
            String objectName = FileIOUtils.getSaveFileName(fileDTO.getNewName());

            String bucketName = aliyunConstants.getBucketName();
            // 创建OSSClient实例。
            String endpoint = aliyunConstants.getEndpoint();
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = aliyunConstants.getKeyId();
            String accessKeySecret = aliyunConstants.getKeySecret();

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            ObjectMetadata meta = new ObjectMetadata();
            // 指定上传的内容类型。
            meta.setContentType(file.getContentType());

            // 通过AppendObjectRequest设置多个参数。
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, objectName, new ByteArrayInputStream(file.getBytes()), meta);
            // 设置文件的追加位置。
            appendObjectRequest.setPosition((long) (fileDTO.getShardIndex() * fileDTO.getShardSize()));
            AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
            //获取下一标记
            FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
            filePo.setPath(objectName);
            //不存在就插入，存在就更新
            if (one != null) {
                filePo.setId(one.getId());
                //文件名不变
                filePo.setName(one.getName());
                this.updateById(filePo);
            } else {
                this.save(filePo);
            }
            ossClient.shutdown();
            //获得地址
            return appendObjectResult.getResponse().getUri();
        } catch (IOException e) {
            log.error("上传有误", e);
            throw new IllegalArgumentException("上传有误");
        }
    }

    @Override
    public String contentUpload(MultipartFile file) {
        try {
            //封装dto然后调用上面的方法
            String extensionName = FileIOUtils.getExtensionName(file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO()
                    .setFile(file)
                    .setName(file.getOriginalFilename())
                    .setSuffix(extensionName)
                    .setUseTo(FileUseEnum.COURSE.getCode())
                    .setFileKey(FileIOUtils.getFileMd5(file.getInputStream()))
                    .setNewName(UUID.randomUUID().toString() + "." + extensionName)
                    .setShardIndex(0)
                    .setShardTotal(1)
                    .setShardSize(Math.toIntExact(file.getSize()))
                    .setSize(Math.toIntExact(file.getSize()));

            return upload(fileDTO);
        } catch (IOException e) {
            log.error("上传有误", e);
            throw new IllegalArgumentException("上传有误");
        }
    }

    private String vod(FileDTO fileDTO) {
        String keyId = aliyunConstants.getKeyId();
        String keySecret = aliyunConstants.getKeySecret();
        MultipartFile file = fileDTO.getFile();
        String fileKey = fileDTO.getFileKey();
        FilePO one = this.getOne(new LambdaQueryWrapper<FilePO>()
                .eq(FilePO::getFileKey, fileKey));
        if (!ObjectUtils.isEmpty(one) && !StringUtils.isEmpty(one.getVod())) {
            return one.getVod();
        }
        String name = fileDTO.getName();
        try {
            //先上传再保存到数据库中
            String vod = VodUtil.uploadVod(keyId, keySecret, name, file.getInputStream());

            if (!ObjectUtils.isEmpty(one)) {
                //不为空就更新 不改文件名
                one.setVod(vod)
                        .setName(fileDTO.getName());
                boolean update = this.updateById(one);
                if (update) {
                    return vod;
                } else {
                    log.error("上传有误");
                    throw new IllegalArgumentException("上传有误");
                }
            }
            FilePO po = CopyUtils.copy(fileDTO, FilePO.class);
            po.setVod(vod);
            boolean save = this.save(po);
            if (save) {
                return vod;
            } else {
                log.error("上传有误");
                throw new IllegalArgumentException("上传有误");
            }
        } catch (IOException e) {
            log.error("上传有误", e);
            throw new IllegalArgumentException("上传有误");
        }
    }

    @Override
    public VideoVodDTO getVodMessage(FileDTO fileDTO) {
        String vod = vod(fileDTO);
        String keyId = aliyunConstants.getKeyId();
        String keySecret = aliyunConstants.getKeySecret();
        try {
            DefaultAcsClient vodClient = VodUtil.initVodClient(keyId, keySecret);
            GetMezzanineInfoResponse mezzanineInfo = VodUtil.getMezzanineInfo(vodClient, vod);
            //视频时长向下取整然后转int
            String mezzanineDuration = mezzanineInfo.getMezzanine().getDuration();
            BigDecimal bigDecimal = new BigDecimal(mezzanineDuration);
            int duration = bigDecimal.setScale(0, BigDecimal.ROUND_DOWN).intValue();

            //将vod配套的信息封装成dto
            return new VideoVodDTO()
                    .setVod(vod)
                    .setDuration(duration)
                    .setUrl(mezzanineInfo.getMezzanine().getFileURL());
        } catch (Exception e) {
            log.error("上传视频出错", e);
            throw new IllegalArgumentException("上传视频出错");
        }
    }

    @Override
    public String getVodAuth(String vod) {
        String keyId = aliyunConstants.getKeyId();
        String keySecret = aliyunConstants.getKeySecret();
        try {
            DefaultAcsClient vodClient = VodUtil.initVodClient(keyId, keySecret);
            GetVideoPlayAuthResponse response = VodUtil.getVideoPlayAuth(vodClient, vod);
            return response.getPlayAuth();
        } catch (Exception e) {
            log.error("获取凭证出错", e);
            throw new IllegalArgumentException("获取凭证出错,可能视频不存在或在转码中");
        }
    }


}
