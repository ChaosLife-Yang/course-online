package com.halayang.server.resource.service.impl;

import com.halayang.server.resource.po.ResourcePO;
import com.halayang.server.resource.mapper.ResourceMapper;
import com.halayang.server.resource.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-02-16
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourcePO> implements ResourceService {

}
