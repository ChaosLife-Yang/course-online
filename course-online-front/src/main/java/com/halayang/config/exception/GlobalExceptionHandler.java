package com.halayang.config.exception;

import com.halayang.common.exception.BusinessException;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
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

    @ExceptionHandler(value = BusinessException.class)
    public ResponseObject<Object> handlerBusinessException(BusinessException exception) {
        return ResponseResult.error(exception.getCode(), exception.getMessage());
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

}