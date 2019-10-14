package com.example.boot.receiver;

import com.example.boot.common.config.PublisherConfirmConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author dengjia
 * @date 2019/9/11 20:09
 */
@Component
@Slf4j
public class CallBackReceiver {

    @RabbitListener(queues = {PublisherConfirmConfig.QUEUE_NAME}, errorHandler = "rabbitListenerErrorHandler")
    public void receive(String request, Message message, Channel channel) throws IOException {
        log.info("开始处理消息：{}", request);
        /*if (1 == 1) {
            throw new CustomException("");
        }*/
        // 确认消息已经消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
