package com.halayang.server.member.controller;

import com.halayang.common.utils.CopyUtils;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.member.dto.LoginDTO;
import com.halayang.server.member.dto.MemberDTO;
import com.halayang.server.member.dto.RegisterDTO;
import com.halayang.server.member.po.MemberPO;
import com.halayang.server.member.service.MemberService;
import com.halayang.server.member.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/30 10:25           1.0
 * @program course-online
 * @description
 * @create 2021/3/30 10:25
 */
@Api(tags = {"登录注册"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录")
    @PostMapping("/login")
    public ResponseObject<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseResult.success(userService.getToken(loginDTO));
    }

    @ApiOperation(value = "获取用户详情", httpMethod = "GET", notes = "获取用户详情")
    @GetMapping("/info/{id}")
    public ResponseObject<MemberDTO> getInfo(@PathVariable String id){
        MemberPO memberPO = memberService.getById(id);
        MemberDTO memberDTO = CopyUtils.copy(memberPO, MemberDTO.class);
        memberDTO.setPassword(null);
        return ResponseResult.success(memberDTO);
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册")
    @PostMapping("/register")
    public ResponseObject<Object> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseResult.success(userService.register(registerDTO));
    }

}
