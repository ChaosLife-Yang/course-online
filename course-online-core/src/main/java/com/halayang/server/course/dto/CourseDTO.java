package com.halayang.server.course.dto;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
@ApiModel(value="CourseDTO对象", description="课程")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "概述")
    private String summary;

    @ApiModelProperty(value = "时长|单位秒")
    private Integer time;

    @ApiModelProperty(value = "价格（元）")
    private BigDecimal price;

    @ApiModelProperty(value = "封面")
    private String image;

    @ApiModelProperty(value = "级别|枚举[CourseLevelEnum]：ONE(1, 初级),TWO(2, 中级),THREE(3, 高级)")
    private String level;

    @ApiModelProperty(value = "收费|枚举[CourseChargeEnum]：CHARGE(C, 收费),FREE(F, 免费)")
    private String charge;

    @ApiModelProperty(value = "状态|枚举[CourseStatusEnum]：PUBLISH(P, 发布),DRAFT(D, 草稿)")
    private String status;

    @ApiModelProperty(value = "报名数")
    private Integer enroll;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "讲师|teacher.id")
    private String teacherId;


}
