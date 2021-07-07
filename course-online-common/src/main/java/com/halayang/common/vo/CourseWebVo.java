package com.halayang.common.vo;

import com.halayang.common.dto.CourseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 11:05           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 11:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "课程展示对象", description = "课程详情VO展示对象")
public class CourseWebVo {

    @ApiModelProperty(value = "课程信息")
    private CourseDTO course;

    @ApiModelProperty(value = "课程下的章节列表")
    private List<ChapterWebVo> chapterList;

}
