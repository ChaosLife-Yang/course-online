package com.halayang.config.log;

import com.halayang.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 * <p>
 * 使用AOP打印日志
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/15 13:09           1.0
 * @program course-online
 * @description
 * @create 2020/12/15 13:09
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("execution(public * com.halayang.server.*.controller.*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        log.info("服务IP地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        log.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        log.info("请求IP地址: {}", request.getRemoteAddr());
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        boolean flag = true;
        for (Object arg : args) {
            if (arg instanceof MultipartFile) {
                flag = false;
                break;
            }
        }
        if (flag) {
            log.info("请求参数: {}", JacksonUtils.toString(args));
        }else {
            log.info("上传文件请求");
        }
        Object result = joinPoint.proceed(args);
        log.info("返回结果: {}", JacksonUtils.toString(result));
        log.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
