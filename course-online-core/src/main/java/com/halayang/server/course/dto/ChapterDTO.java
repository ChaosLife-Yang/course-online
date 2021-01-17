package com.halayang.server.course.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 大章DTO对象
 *
 * @author YangYuDi
 * @since 2020-12-16 11:04:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ChapterDTO对象", description = "大章DTO数据交互对象")
public class ChapterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotBlank(message = "课程不为空")
    private String courseId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不为空")
    private String name;

    @ApiModelProperty(value = "顺序")
    private Integer sort;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime editTime;


}