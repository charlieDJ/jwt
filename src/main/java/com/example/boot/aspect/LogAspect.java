package com.example.boot.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切点
     */
    @Pointcut("execution (public * com.example.boot.controller..*.*(..))")
    public void log() {
    }

    /**
     * 请求方法前打印内容
     *
     * @param joinPoint
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(Instant.now().toEpochMilli());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = request.getMethod();
        StringBuilder params = new StringBuilder();
        log.info("Request URL >>>>>> {}", request.getRequestURI());
        if (HttpMethod.GET.toString().equals(method)) {
            String queryString = request.getQueryString();
            if (StringUtils.hasText(queryString)) {
                try {
                    params.append(URLDecoder.decode(queryString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    log.info("URL解码失败");
                }
            }
        } else { //其他请求
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray != null && paramsArray.length > 0) {
                for (int i = 0; i < paramsArray.length; i++) {
                    if (paramsArray[i] instanceof Serializable) {
                        params.append(paramsArray[i].toString()).append(",");
                    } else {
                        //使用json系列化 反射等等方法 反系列化会影响请求性能建议重写tostring方法实现系列化接口
                        try {
                            if (paramsArray[i] instanceof HttpServletRequest) {
                                continue;
                            }
                            String param = objectMapper.writeValueAsString(paramsArray[i]);
                            if (StringUtils.hasText(param)) {
                                params.append(param).append(",");
                            }
                        } catch (JsonProcessingException e) {
                            log.error("doBefore obj to json exception obj={},msg={}", paramsArray[i], e);
                        }
                    }
                }
            }
        }
        log.info("Request Params >>>>>> method={},params={}", method, params);
        Object resultObj = joinPoint.proceed();
        Object result = resultObj;
        if (result instanceof Serializable) {
            result = result.toString();
        } else {
            if (result != null) {
                try {
                    result = objectMapper.writeValueAsString(result);
                } catch (JsonProcessingException e) {
                    log.error("doAfter Returning obj to json exception obj={},msg={}", result, e);
                }
            }
        }
        log.info("Response Result <<<<<< result={}", result);
        long spendTime = Instant.now().toEpochMilli() - startTime.get();
        log.info("SPEND TIME: {} mills", Instant.now().toEpochMilli() - startTime.get());
        startTime.remove();
        return resultObj;
    }
}
