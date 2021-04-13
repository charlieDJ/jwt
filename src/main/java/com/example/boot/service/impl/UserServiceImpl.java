package com.example.boot.service.impl;

import com.example.boot.common.util.JwtTokenUtil;
import com.example.boot.dao.mapper.UserMapper;
import com.example.boot.entity.JwtUser;
import com.example.boot.entity.User;
import com.example.boot.exception.CustomException;
import com.example.boot.model.Response;
import com.example.boot.model.request.RegisterRequest;
import com.example.boot.model.request.UmsAdminLoginParam;
import com.example.boot.model.response.TokenResponse;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dengjia
 * @date 2019/8/15 16:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Response<Object> register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        // 记得注册的时候把密码加密一下
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        userMapper.insertSelective(user);
        return Response.success();
    }

    @Override
    public Response<TokenResponse> login(UmsAdminLoginParam request) {
        Optional<User> userOpt = userMapper.getByName(request.getUsername());
        User user = userOpt.get();
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("密码不正确");
        }
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(user.getUsername())
                .setPassword(user.getPassword());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(jwtUser);
        // 也可以把token放在响应头里
        TokenResponse response = new TokenResponse();
        response.setToken(token)
                .setTokenHead(tokenHead);
        return Response.success(response);
    }
}
