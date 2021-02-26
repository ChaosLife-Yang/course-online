package com.halayang.server.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.common.utils.BCryptUtils;
import com.halayang.common.utils.CopyUtils;
import com.halayang.server.client.po.OauthClientDetailsPO;
import com.halayang.server.client.mapper.OauthClientDetailsMapper;
import com.halayang.server.client.service.OauthClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-26
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetailsPO> implements OauthClientDetailsService {

    @Override
    public boolean addClientDetails(OauthClientDetailsDTO clientDetailsDTO) {
        OauthClientDetailsPO clientDetailsPO = CopyUtils.copy(clientDetailsDTO, OauthClientDetailsPO.class);
        int count = this.count(new LambdaQueryWrapper<OauthClientDetailsPO>()
                .eq(OauthClientDetailsPO::getClientId, clientDetailsPO.getClientId()));
        //已添加就不重复添加了
        if (count > 0) {
            return false;
        }
        clientDetailsPO.setClientSecret(BCryptUtils.encode(clientDetailsDTO.getClientSecret()));
        return this.save(clientDetailsPO);
    }
}
