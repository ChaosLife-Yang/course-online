package com.halayang.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * teacherDTO对象
 *
 * @author YangYuDi
 * @since 2021-01-11 13:41:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TeacherDTO对象", description = "teacherDTO数据交互对象")
public class TeacherDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 昵称
    */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
    * 头像
    */
    @ApiModelProperty(value = "头像")
    private String image;
    /**
    * 职位
    */
    @ApiModelProperty(value = "职位")
    private String position;
    /**
    * 座右铭
    */
    @ApiModelProperty(value = "座右铭")
    private String motto;
    /**
    * 简介
    */
    @ApiModelProperty(value = "简介")
    private String intro;
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