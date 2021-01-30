package com.halayang.server.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description 上传成功后返回的封装对象
 * @author YangYuDi
 * @create 2021/1/30 17:46
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/1/30 17:46           1.0                         上传成功后返回的封装对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "VideoVodDTO对象", description = "vod上传成功后返回的封装对象")
public class VideoVodDTO {

    @ApiModelProperty(value = "vod值")
    private String vod;

    @ApiModelProperty(value = "播放地址")
    private String url;

}
