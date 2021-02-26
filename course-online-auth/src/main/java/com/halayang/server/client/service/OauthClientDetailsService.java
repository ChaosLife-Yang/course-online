package com.halayang.server.client.service;

import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.server.client.po.OauthClientDetailsPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-26
 */
public interface OauthClientDetailsService extends IService<OauthClientDetailsPO> {

    /**
     * 添加oauth2被管理客户端信息
     *
     * @param oauthClientDetailsDTO oauth2客户端信息dto
     * @author YangYudi
     * @date 2021/2/26 13:49
     * @return void
     */
    boolean addClientDetails(OauthClientDetailsDTO oauthClientDetailsDTO);

}
