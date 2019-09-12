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

    @RabbitListener(queues = {PublisherConfirmConfig.QUEUE_NAME})
    public void receive(String request, Message message, Channel channel) throws IOException {
        try {
            log.info("开始处理消息：{}", request);
            // 确认消息已经消费成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("MQ消息处理异常，消息ID：{}，消息体:{}", message.getMessageProperties().getCorrelationId(),
                    request, e);
            try {
                // 确认消息已经消费成功
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e1) {
                log.error("保存异常MQ消息到数据库异常，放到死性队列，消息ID：{}", message.getMessageProperties().getCorrelationId());
                // 确认消息将消息放到死信队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }
    }
}
