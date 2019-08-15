package com.example.boot.receiver;

import com.example.boot.common.config.MakeCardDelayConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dengjia
 * @date 2019/8/13 16:53
 */
@Component
@Slf4j
public class DelayReceiver {


    @RabbitListener(queues = {MakeCardDelayConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(String orderId, Message message, Channel channel) {
        log.info("消费消息：{}", orderId);
    }
}
