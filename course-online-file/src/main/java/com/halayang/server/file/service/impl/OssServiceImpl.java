package com.halayang.server.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.file.mapper.FileMapper;
import com.halayang.server.file.po.FilePO;
import com.halayang.server.file.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description 
 * @author YangYuDi
 * @create 2021/1/26 10:12
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/1/26 10:12           1.0                         
 */
@Slf4j
@Service
public class OssServiceImpl extends ServiceImpl<FileMapper, FilePO> implements OssService {
}
