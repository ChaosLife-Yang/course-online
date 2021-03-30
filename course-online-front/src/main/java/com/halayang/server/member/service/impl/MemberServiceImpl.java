package com.halayang.server.member.service.impl;

import com.halayang.server.member.po.MemberPO;
import com.halayang.server.member.mapper.MemberMapper;
import com.halayang.server.member.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-03-30
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberPO> implements MemberService {

}
