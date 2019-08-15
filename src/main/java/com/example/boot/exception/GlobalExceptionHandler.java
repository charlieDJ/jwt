package com.example.boot.exception;

import com.example.boot.common.enumeration.ResponseCodes;
import com.example.boot.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.message.AuthException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(AuthException.class)
    public Response handleRuntimeError(AuthException e) {
        logRequestError(e);
        return Response.of(ResponseCodes.TOKEN_ERROR.getCode(), e.getMessage());
    }


    /**
     * 校验参数异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleRuntimeError(MethodArgumentNotValidException e) {
        logRequestError(e);
        return Response.of(ResponseCodes.SYSTEM_ERROR.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 校验参数异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleRuntimeError(ConstraintViolationException e) {
        logRequestError(e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        Object[] objects = violations.toArray();
        ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) objects[0];
        return Response.of(ResponseCodes.SYSTEM_ERROR.getCode(), constraintViolation.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Response handleRuntimeError(CustomException e) {
        logRequestError(e);
        if (StringUtils.hasText(e.getCode())) {
            return Response.of(e.getCode(), e.getMessage(), new ArrayList<>());
        }
        return Response.of(ResponseCodes.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理Spring框架Assert校验抛出的异常
     *
     * @param e 错误异常
     * @return 异常消息
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleRuntimeError(IllegalArgumentException e) {
        logRequestError(e);
        return Response.of(ResponseCodes.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Response handleRequestError(Throwable e) {
        log.error("请求出错: {}, 具体原因：{}", e.getMessage(), e);
        return Response.of(ResponseCodes.SYSTEM_ERROR.getCode(), ResponseCodes.SYSTEM_ERROR.getMsg());
    }


    private void logRequestError(Exception e) {
        log.error("请求出错: {}, 具体原因：{}", e.getMessage(), e);
    }

}
