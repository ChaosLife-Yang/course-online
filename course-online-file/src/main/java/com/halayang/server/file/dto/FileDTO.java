package com.halayang.server.file.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 相对路径
    */
    @ApiModelProperty(value = "相对路径")
    private String path;
    /**
    * 文件名
    */
    @ApiModelProperty(value = "文件名")
    private String name;
    /**
    * 后缀
    */
    @ApiModelProperty(value = "后缀")
    private String suffix;
    /**
    * 大小|字节B
    */
    @ApiModelProperty(value = "大小|字节B")
    private Integer size;
    /**
    * 用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)
    */
    @ApiModelProperty(value = "用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)")
    private String useTo;
    /**
    * 已上传分片
    */
    @ApiModelProperty(value = "已上传分片")
    private Integer shardIndex;
    /**
    * 分片大小|B
    */
    @ApiModelProperty(value = "分片大小|B")
    private Integer shardSize;
    /**
    * 分片总数
    */
    @ApiModelProperty(value = "分片总数")
    private Integer shardTotal;
    /**
    * 文件标识
    */
    @ApiModelProperty(value = "文件标识")
    private String fileKey;
    /**
    * vod|阿里云vod
    */
    @ApiModelProperty(value = "vod|阿里云vod")
    private String vod;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime editTime;


}