package com.halayang.server.client.controller;


import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.client.service.OauthClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * oauth2客户端注册
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-26
 */
@Api(tags = {"oauth2客户端注册"})
@RestController
@RequestMapping("/client")
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService clientDetailsService;

    @ApiOperation(value = "添加客户端", httpMethod = "POST", notes = "添加客户端详情")
    @PostMapping
    public ResponseObject<String> addClientDetail(@ApiParam @Validated @RequestBody OauthClientDetailsDTO clientDetail) {
        boolean res = clientDetailsService.addClientDetails(clientDetail);
        return res ? ResponseResult.success("添加客户端详情成功") : ResponseResult.success("客户端详情已存在");
    }

}
