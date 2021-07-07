package com.halayang.server.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/26 20:51           1.0
 * @program course-online
 * @description
 * @create 2021/1/26 20:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FileMessageDTO对象", description = "文件数据回显交互对象")
public class FileMessageDTO {

    @ApiModelProperty(value = "用于保存的新文件名")
    private String newName;

    @ApiModelProperty(value = "已上传分片")
    private Integer shardIndex;
}
