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
 * CourseDTO对象
 *
 * @author YangYuDi
 * @since 2021-01-09 21:59:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseContentDTO对象", description = "CourseDTO数据交互对象")
public class CourseContentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 课程id
    */
    @ApiModelProperty(value = "课程id")
    @NotBlank(message = "课程id不为空")
    private String id;
    /**
    * 课程内容
    */
    @ApiModelProperty(value = "课程内容")
    private String content;
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