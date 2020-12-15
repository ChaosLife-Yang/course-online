package com.halayang.server.section.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 小节
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("section")
@ApiModel(value = "SectionPO对象", description = "小节")
public class SectionPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "课程|course.id")
    private String courseId;

    @ApiModelProperty(value = "大章|chapter.id")
    private String chapterId;

    @ApiModelProperty(value = "视频")
    private String video;

    @ApiModelProperty(value = "时长|单位秒")
    private Integer time;

    @ApiModelProperty(value = "收费|C 收费；F 免费")
    private String charge;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "vod|阿里云vod")
    private String vod;


}
