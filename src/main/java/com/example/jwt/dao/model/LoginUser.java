package com.example.jwt.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class LoginUser {
    private String username;
    private String password;
    private Integer rememberMe;
}
