package com.example.boot.config.security;

import com.example.boot.dao.mapper.UserMapper;
import com.example.boot.entity.JwtUser;
import com.example.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static Map<String, JwtUser> userMap = new HashMap<>();

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        JwtUser jwtUser = userMap.get(s);
        if (Objects.nonNull(jwtUser)) {
            return jwtUser;
        }
        Optional<User> userOpt = userMapper.getByName(s);
        if (!userOpt.isPresent()) {
            return null;
        }
        JwtUser newJwtUser = new JwtUser(userOpt.get());
        userMap.put(s, newJwtUser);
        return newJwtUser;
    }

}
