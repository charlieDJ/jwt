package com.example.boot.mapper;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ParamTest extends JwtApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void paramTest(){
        log.info(userMapper.getByName("dj").toString());
    }

}
