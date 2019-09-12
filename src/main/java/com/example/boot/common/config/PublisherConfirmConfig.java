package com.example.boot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengjia
 * @date 2019/9/10 11:42
 */
@Configuration
@Slf4j
public class PublisherConfirmConfig {
    public static final String EXCHANGE_NAME = "topic.publisher";
    public static final String QUEUE_NAME = "topic.publisher.queue";
    private static final String ROUTING_KEY = "topic.publisher.#";

    @Bean
    TopicExchange publisherExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue publisherQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(publisherQueue()).to(publisherExchange()).with(ROUTING_KEY);
    }

}
