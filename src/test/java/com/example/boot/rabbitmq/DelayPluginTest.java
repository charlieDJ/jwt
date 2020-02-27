package com.example.boot.rabbitmq;

import com.example.boot.JwtApplicationTests;
import com.example.boot.sender.DelayProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DelayPluginTest extends JwtApplicationTests {

    @Autowired
    private DelayProducer delayProducer;

    @Test
    public void delayTest(){
        delayProducer.sendMsg();
    }


}
