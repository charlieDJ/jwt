package com.example.boot.sender;

import com.example.boot.common.config.PublisherConfirmConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author dengjia
 * @date 2019/9/10 11:48
 */
@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey, String message) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("发送消息，消息id:" + correlationData.getId());
        // 用RabbitMQ发送MQTT需将exchange配置为amq.topic
        this.rabbitTemplate.convertAndSend(PublisherConfirmConfig.EXCHANGE_NAME, routingKey, message, correlationData);
    }

    /**
     * 服务端确认 ack=true
     *
     * @param correlationData 关联数据
     * @param ack             是否确认
     * @param cause           错误原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息已确认，消息id:" + correlationData.getId());
        if (ack) {
            System.out.println("消息发送确认成功");
        } else {
            System.out.println("消息发送确认失败:" + cause);
        }
    }

    /**
     * 找不对队列时回调，属性 template.mandatory=true
     *
     * @param message    消息
     * @param replyCode  回应码
     * @param replyText  回应文本
     * @param exchange   交换机
     * @param routingKey 路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}
