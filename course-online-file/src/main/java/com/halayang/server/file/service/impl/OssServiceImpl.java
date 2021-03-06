package com.halayang.server.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
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
            //????????????????????????????????????????????????????????????????????????
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
            // ??????OSSClient?????????
            String endpoint = aliyunConstants.getEndpoint();
            // ??????????????????AccessKey????????????API???????????????????????????????????????????????????????????????RAM????????????API?????????????????????????????????RAM???????????????RAM?????????
            String accessKeyId = aliyunConstants.getKeyId();
            String accessKeySecret = aliyunConstants.getKeySecret();

            // ??????OSSClient?????????
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            ObjectMetadata meta = new ObjectMetadata();
            // ??????????????????????????????
            meta.setContentType(file.getContentType());

            // ??????AppendObjectRequest?????????????????????
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, objectName, new ByteArrayInputStream(file.getBytes()), meta);
            // ??????????????????????????????
            appendObjectRequest.setPosition((long) (fileDTO.getShardIndex() * fileDTO.getShardSize()));
            AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
            //??????????????????
            FilePO filePo = CopyUtils.copy(fileDTO, FilePO.class);
            filePo.setPath(objectName);
            //????????????????????????????????????
            if (one != null) {
                filePo.setId(one.getId());
                //???????????????
                filePo.setName(one.getName());
                this.updateById(filePo);
            } else {
                this.save(filePo);
            }
            ossClient.shutdown();
            //????????????
            return appendObjectResult.getResponse().getUri();
        } catch (IOException e) {
            log.error("????????????", e);
            throw new IllegalArgumentException("????????????");
        }
    }

    @Override
    public String contentUpload(MultipartFile file) {
        try {
            //??????dto???????????????????????????
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
            log.error("????????????", e);
            throw new IllegalArgumentException("????????????");
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
            //?????????????????????????????????
            String vod = VodUtil.uploadVod(keyId, keySecret, name, file.getInputStream());

            if (!ObjectUtils.isEmpty(one)) {
                //?????????????????? ???????????????
                one.setVod(vod)
                        .setName(fileDTO.getName());
                boolean update = this.updateById(one);
                if (update) {
                    return vod;
                } else {
                    log.error("????????????");
                    throw new IllegalArgumentException("????????????");
                }
            }
            FilePO po = CopyUtils.copy(fileDTO, FilePO.class);
            po.setVod(vod);
            boolean save = this.save(po);
            if (save) {
                return vod;
            } else {
                log.error("????????????");
                throw new IllegalArgumentException("????????????");
            }
        } catch (IOException e) {
            log.error("????????????", e);
            throw new IllegalArgumentException("????????????");
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
            //?????????????????????????????????int
            String mezzanineDuration = mezzanineInfo.getMezzanine().getDuration();
            BigDecimal bigDecimal = new BigDecimal(mezzanineDuration);
            int duration = bigDecimal.setScale(0, BigDecimal.ROUND_DOWN).intValue();

            //???vod????????????????????????dto
            return new VideoVodDTO()
                    .setVod(vod)
                    .setDuration(duration)
                    .setUrl(mezzanineInfo.getMezzanine().getFileURL());
        } catch (Exception e) {
            log.error("??????????????????", e);
            throw new IllegalArgumentException("??????????????????");
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
            log.error("??????????????????", e);
            throw new IllegalArgumentException("??????????????????,????????????????????????????????????");
        }
    }


}
