package com.halayang.config.exception;

import com.halayang.common.utils.response.ResponseCode;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/15 13:20           1.0
 * @program course-online
 * @description
 * @create 2020/12/15 13:20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseObject<Object> error(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ResponseResult.error(e.getMessage());
    }

    /**
     * 捕获验证注解抛出的异常
     *
     * @param e MethodArgumentNotValidException
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.Object>
     * @author YangYudi
     * @date 2020/12/15 14:44
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObject<Object> exceptionHandler(MethodArgumentNotValidException e) {
        String defaultMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.error(defaultMessage);
        return ResponseResult.error(defaultMessage);
    }

    /**
     * 客户端未认证
     *
     * @param e org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException 异常
     * @return
     * @author YeMeng
     * @date 2019/12/24 16:53
     */
    @ExceptionHandler(UnauthorizedClientException.class)
    public ResponseObject<Object> handleUnauthorizedClientException(UnauthorizedClientException e) {
        return ResponseResult.error(ResponseCode.UNAUTHORIZED_CLIENT, e.getMessage());
    }

}