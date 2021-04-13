package com.example.boot.config.security;

import com.alibaba.fastjson.JSON;
import com.example.boot.common.util.ResponseHelper;
import com.example.boot.model.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一处理被403响应的事件，通常是未登录
 * @author dj
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ResponseHelper.setResponse(response, JSON.toJSONString(Response.auth("用户尚未登录或登录信息过期")));
    }
}
