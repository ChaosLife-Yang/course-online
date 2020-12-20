package com.halayang.server.course.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 课程DTO对象
 *
 * @author YangYuDi
 * @since 2020-12-16 11:05:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseDTO对象", description = "课程DTO数据交互对象")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不为空")
    private String name;
    /**
    * 概述
    */
    @ApiModelProperty(value = "概述")
    private String summary;
    /**
    * 时长|单位秒
    */
    @ApiModelProperty(value = "时长|单位秒")
    private Integer time;
    /**
    * 价格（元）
    */
    @ApiModelProperty(value = "价格（元）")
    private BigDecimal price;
    /**
    * 封面
    */
    @ApiModelProperty(value = "封面")
    private String image;
    /**
    * 级别|枚举[CourseLevelEnum]：ONE(1, 初级),TWO(2, 中级),THREE(3, 高级)
    */
    @ApiModelProperty(value = "级别|枚举[CourseLevelEnum]：ONE(1, 初级),TWO(2, 中级),THREE(3, 高级)")
    private String level;
    /**
    * 收费|枚举[CourseChargeEnum]：CHARGE(C, 收费),FREE(F, 免费)
    */
    @ApiModelProperty(value = "收费|枚举[CourseChargeEnum]：CHARGE(C, 收费),FREE(F, 免费)")
    private String charge;
    /**
    * 状态|枚举[CourseStatusEnum]：PUBLISH(P, 发布),DRAFT(D, 草稿)
    */
    @ApiModelProperty(value = "状态|枚举[CourseStatusEnum]：PUBLISH(P, 发布),DRAFT(D, 草稿)")
    private String status;
    /**
    * 报名数
    */
    @ApiModelProperty(value = "报名数")
    private Integer enroll;
    /**
    * 顺序
    */
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
    * 修改时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date editTime;
    /**
    * 讲师|teacher.id
    */
    @ApiModelProperty(value = "讲师|teacher.id")
    private String teacherId;


}