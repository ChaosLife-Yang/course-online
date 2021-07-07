package com.halayang.common.dto;

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
 * YangYuDi               2021/3/12 17:28           1.0
 * @program course-online
 * @description
 * @create 2021/3/12 17:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PopularAndNewCourseDTO对象", description = "前台展示页面热门课程与新上好课dto")
public class PopularAndNewCourseDTO {

    @ApiModelProperty(value = "热门课程")
    private List<CourseDTO> popular;

    @ApiModelProperty(value = "新上好课")
    private List<CourseDTO> fresh;

}
