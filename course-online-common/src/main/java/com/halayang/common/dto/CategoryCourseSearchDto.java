package com.halayang.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/25 15:44           1.0
 * @program course-online
 * @description
 * @create 2021/3/25 15:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CategoryCourseSearchDto对象", description = "根据分类查课程DTO对象")
public class CategoryCourseSearchDto {

    String level1;
    String level2;
    Integer page;

}
