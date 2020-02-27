package com.example.boot.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq 安装了延时插件才能启用这个功能
 */
@Configuration
public class DelayPluginConfig {
    public static final String exchangeName = "test_exchange";
    public static final String queueName = "test_queue";
    public static final String routingKey = "test_queue";

    /**
     * 创建延迟队列
     */
    @Bean
    public Queue createQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    /**
     * 创建路由
     */
    @Bean
    public CustomExchange createExchange() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange(
                exchangeName, "x-delayed-message", true, false, map);
    }

    /**
     * 绑定路由与队列
     */
    @Bean
    public Binding exchangeBindingQueue() {
        return BindingBuilder.bind(createQueue()).
                to(createExchange()).with(routingKey).noargs();
    }

}
