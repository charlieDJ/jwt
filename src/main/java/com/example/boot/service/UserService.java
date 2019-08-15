package com.example.boot.service;

import com.example.boot.model.Response;
import com.example.boot.model.request.RegisterRequest;
import com.example.boot.model.request.UmsAdminLoginParam;
import com.example.boot.model.response.TokenResponse;

/**
 * @author dengjia
 * @date 2019/8/15 16:03
 */
public interface UserService {
    Response register(RegisterRequest request);

    Response<TokenResponse> login(UmsAdminLoginParam request);
}
