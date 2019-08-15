package com.example.boot.controller;

import com.example.boot.model.Response;
import com.example.boot.model.request.RegisterRequest;
import com.example.boot.model.request.UmsAdminLoginParam;
import com.example.boot.model.response.TokenResponse;
import com.example.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户登录、鉴权接口"})
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/register")
    public Response registerUser(@Validated @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response<TokenResponse> login(@Validated @RequestBody UmsAdminLoginParam request) {
        return userService.login(request);
    }
}
