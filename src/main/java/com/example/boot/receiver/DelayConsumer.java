package com.example.boot.receiver;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 消费者
 */
@Component
public class DelayConsumer {

    @RabbitListener(queues = "test_queue")
    public void receive(String msg, Message message, Channel channel) throws IOException {
        System.out.println("接收到的消息:" + msg + "||" + LocalDateTime.now());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}