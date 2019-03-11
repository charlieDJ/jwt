package com.example.jwt.config;

import com.alibaba.fastjson.JSON;
import com.example.jwt.model.Response;
import com.example.jwt.util.ResponseHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一处理被403响应的事件
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ResponseHelper.setResponse(response, JSON.toJSONString(Response.auth("用户尚未登录或登录信息过期")));
    }
}
