package com.halayang.server.file.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * fileDTO对象
 *
 * @author YangYuDi
 * @since 2021-01-12 21:08:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FileDTO对象", description = "fileDTO数据交互对象")
public class FileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @JsonIgnore
    @ApiModelProperty(value = "文件")
    private transient MultipartFile file;

    @ApiModelProperty(value = "相对路径")
    private String path;

    @ApiModelProperty(value = "用于保存的新文件名")
    private String newName;

    @ApiModelProperty(value = "源文件名")
    private String name;

    @ApiModelProperty(value = "后缀")
    private String suffix;

    @ApiModelProperty(value = "大小|字节B")
    private Integer size;

    @ApiModelProperty(value = "用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)")
    private String useTo;

    @ApiModelProperty(value = "已上传分片")
    private Integer shardIndex;

    @ApiModelProperty(value = "分片大小|B")
    private Integer shardSize;

    @ApiModelProperty(value = "分片总数")
    private Integer shardTotal;

    @ApiModelProperty(value = "文件标识")
    private String fileKey;

    @ApiModelProperty(value = "vod|阿里云vod")
    private String vod;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime editTime;


}