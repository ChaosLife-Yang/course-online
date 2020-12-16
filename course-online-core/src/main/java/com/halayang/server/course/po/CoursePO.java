package com.halayang.server.course.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
@ApiModel(value = "CoursePO对象", description = "课程")
public class CoursePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @JsonIgnore
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "讲师|teacher.id")
    private String teacherId;


}
