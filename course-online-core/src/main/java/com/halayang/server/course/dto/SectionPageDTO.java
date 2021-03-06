package com.halayang.server.course.dto;

import com.halayang.server.course.po.SectionPO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/27 19:45           1.0
 * @program course-online
 * @description
 * @create 2020/12/27 19:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SectionPageDTO {

    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotBlank(message = "课程不为空")
    private String courseId;

    /**
     * 大章id
     */
    @ApiModelProperty(value = "大章id")
    @NotBlank(message = "课程大章不为空")
    private String chapterId;

    /**
     * 当前页
     */
    @NotNull
    @ApiModelProperty(value = "当前页")
    @Range(min = 1, message = "页码不小于1")
    private Long page;
    /**
     * 当前页行数
     */
    @NotNull
    @ApiModelProperty(value = "当前页行数")
    @Range(min = 1, message = "每页行数不小于1")
    private Long size;

    /**
     * 共多少页
     */
    private Integer pages;

    /**
     * 数据
     */
    private List<SectionPO> list;
}
