package com.halayang.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * oauth2客户端信息DTO对象
 *
 * @author YangYuDi
 * @since 2021-02-26 13:31:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthClientDetailsDTO对象", description = "oauth2客户端信息DTO数据交互对象")
public class OauthClientDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            value = "客户端id",
            dataType = "String",
            required = true,
            example = "core"
    )
    @NotBlank(message = "客户端id不能为空")
    private String clientId;

    @ApiModelProperty(
            value = "客户端所能访问的资源id集合,多个资源时用逗号,分隔",
            dataType = "String",
            required = true,
            example = "core"
    )
    @NotBlank(message = "资源id不能为空")
    private String resourceIds;

    @ApiModelProperty(
            value = "客户端密钥",
            dataType = "String",
            required = true,
            example = "123456"
    )
    @NotBlank(message = "客户端密钥不能为空")
    private String clientSecret;

    @ApiModelProperty(
            value = "客户端申请的权限范围,用逗号,分隔,可选值包括read,write等",
            dataType = "String",
            required = true,
            example = "server"
    )
    @NotBlank(message = "当前权限范围不能为空")
    private String scope;

    @ApiModelProperty(
            value = "客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号,分隔",
            dataType = "String",
            required = true,
            example = "authorization_code,refresh_token,password"
    )
    @NotBlank(
            message = "当前客户端支持的grant_type不能为空"
    )
    private String authorizedGrantTypes;

    @ApiModelProperty(
            value = "客户端的重定向URI",
            dataType = "String",
            example = "http://localhost:9000/callback"
    )
    private String webServerRedirectUri;

    @ApiModelProperty(
            value = "指定客户端所拥有的Spring Security的权限值,逗号分隔",
            dataType = "String",
            example = ""
    )
    private String authorities;

    @ApiModelProperty(
            value = "设定客户端的access_token的有效时间值(单位:秒)",
            dataType = "Integer",
            required = true,
            example = "1800"
    )
    @DecimalMin(
            message = "当前客户端的认证令牌 access_token有效时间,应当大于等于{value}",
            value = "10"
    )
    @DecimalMax(
            message = "当前客户端的access_token有效时间,应当小于等于{value}",
            value = "999999"
    )
    private Integer accessTokenValidity;

    @ApiModelProperty(
            value = "设定客户端的刷新令牌 refresh_token的有效时间值(单位:秒)默认一个月",
            dataType = "Integer",
            required = true,
            example = "259200"
    )
    @DecimalMin(
            message = "当前客户端的refresh_token有效时间,应当大于等于{value}",
            value = "10"
    )
    @DecimalMax(
            message = "当前客户端的refresh_token有效时间,应当小于等于{value}",
            value = "999999"
    )
    private Integer refreshTokenValidity;

    @ApiModelProperty(
            value = "其他信息, JSON格式",
            dataType = "String",
            example = ""
    )
    @Length(
            message = "当前其他信息长度应该在{max}之内",
            max = 2048
    )
    private String additionalInformation;

    @ApiModelProperty(
            value = "是否自动Approval操作",
            dataType = "String",
            required = true,
            example = "true"
    )
    private String autoapprove;


}