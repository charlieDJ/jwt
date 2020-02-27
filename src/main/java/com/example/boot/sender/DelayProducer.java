package com.example.boot.sender;

import com.example.boot.common.config.DelayPluginConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 延迟消息 发布者
 */
@Component
public class DelayProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMsg(){

        String msg = "测试延时de消息|"+LocalDateTime.now();

        rabbitTemplate.convertAndSend(DelayPluginConfig.exchangeName, DelayPluginConfig.routingKey, msg, (message) ->{
            message.getMessageProperties().setHeader("x-delay", 9000); //延迟9秒
            return message;
        });

    }


}
