package com.halayang.server.member.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户DTO对象
 *
 * @author YangYuDi
 * @since 2021-03-30 09:07:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MemberDTO对象", description = "用户DTO数据交互对象")
public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    * 手机号
    */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
    * 昵称
    */
    @ApiModelProperty(value = "昵称")
    private String name;
    /**
    * 头像url
    */
    @ApiModelProperty(value = "头像url")
    private String photo;
    /**
    * 注册时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
    * 
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime editTime;


}