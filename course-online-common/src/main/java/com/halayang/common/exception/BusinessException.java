package com.halayang.common.exception;

import com.halayang.common.utils.response.ResponseCode;
import lombok.Getter;

/**
 * copyright (C), 2019, 北京同创永益科技发展有限公司
 *
 * @program istorm-automation-core
 * 自定义业务异常
 * @author WangMingshuai
 * @create 2020/08/08 17:03
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * WangMingshuai         2020/08/08 17:03             1.0.0                    自定义业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    /**
     * 自定义业务异常
     * @param errorCode  响应状态枚举类
     * @author WangMingshuai
     * @date 2019/08/08 16:16
     */
    public BusinessException(ResponseCode errorCode) {
      super(errorCode.message());
      this.code = errorCode.code();
    }

    /**
     * 自定义业务异常
     * @param code  异常码
     * @param message  异常码描述
     * @author WangMingshuai
     * @date 2019/08/08 16:17
     */
    public BusinessException(Integer code, String message) {
      super(message);
      this.code = code;
    }

}
