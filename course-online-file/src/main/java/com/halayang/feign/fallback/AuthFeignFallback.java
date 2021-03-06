package com.halayang.feign.fallback;

import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.feign.AuthFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 * @program course-online
 * @description 
 * @author YangYuDi
 * @create 2021/3/6 11:44
 * @version 1.0.0
 *  <author>                <time>                  <version>                   <description>
 *  YangYuDi               2021/3/6 11:44           1.0                         
 */
@Component
public class AuthFeignFallback implements AuthFeign {
    @Override
    public ResponseObject<List<String>> getUserResources(String id) {
        return ResponseResult.error();
    }

    @Override
    public ResponseObject<String> addClientDetail(OauthClientDetailsDTO clientDetail) {
        return ResponseResult.error();
    }
}
