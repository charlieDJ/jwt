package com.example.boot.controller;

import com.example.boot.dao.mapper.UserMapper;
import com.example.boot.entity.User;
import com.example.boot.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dj
 * @date 2021/4/12
 */
@Api(tags = {"用户接口"})
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @GetMapping("/users")
    public Response<List<User>> registerUser() {
        return Response.success(userMapper.getAll());
    }

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @GetMapping("/getByName")
    public Response<User> getByName(@RequestParam("name") String name) {
        return Response.success(userMapper.getByName(name).orElseGet(User::new));
    }
}
