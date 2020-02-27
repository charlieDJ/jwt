package com.example.boot.mapper;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.OrderMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderTest extends JwtApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getById(){
        orderMapper.getById(1).ifPresent(System.out::println);
    }

}
