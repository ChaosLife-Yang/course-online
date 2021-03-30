package com.halayang.server.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.common.utils.JwtUtils;
import com.halayang.server.member.dto.LoginDTO;
import com.halayang.server.member.dto.RegisterDTO;
import com.halayang.server.member.mapper.MemberMapper;
import com.halayang.server.member.po.MemberPO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/30 11:29           1.0
 * @program course-online
 * @description
 * @create 2021/3/30 11:29
 */
@Service
public class UserService {

    @Resource
    private MemberMapper memberMapper;

    public String getToken(LoginDTO loginDTO) {
        MemberPO memberPO = memberMapper.selectOne(new LambdaQueryWrapper<MemberPO>()
                .eq(MemberPO::getMobile, loginDTO.getMobile()));
        Assert.notNull(memberPO, "账号不存在");
        //验证密码
        boolean verify = BCryptUtils.verify(loginDTO.getPassword(), memberPO.getPassword());
        Assert.isTrue(verify, "密码错误");
        return JwtUtils.getJwtToken(memberPO.getId(), memberPO.getName());
    }

    public String register(RegisterDTO registerDTO) {
        Integer count = memberMapper.selectCount(new LambdaQueryWrapper<MemberPO>()
                .eq(MemberPO::getMobile, registerDTO.getMobile()));
        Assert.isTrue(count == 0, "手机号已被注册");
        MemberPO memberPO = new MemberPO()
                .setMobile(registerDTO.getMobile())
                .setName(registerDTO.getName())
                .setPassword(BCryptUtils.encode(registerDTO.getPassword()))
                .setPhoto(registerDTO.getPhoto())
                .setRegisterTime(LocalDateTime.now());
        int insert = memberMapper.insert(memberPO);
        Assert.isTrue(insert > 0, "注册失败");
        return JwtUtils.getJwtToken(memberPO.getId(), memberPO.getName());
    }

}
