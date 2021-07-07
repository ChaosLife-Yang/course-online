package com.halayang.feign.fallback;

import com.halayang.common.dto.OauthClientDetailsDTO;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.feign.AuthFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/6 11:44           1.0
 * @program course-online
 * @description
 * @create 2021/3/6 11:44
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
