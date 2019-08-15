package com.example.boot.sender;

import com.example.boot.common.config.MakeCardDelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dengjia
 * @date 2019/8/13 16:48
 */
@Slf4j
@Component
public class DelaySender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDelay(String orderId) {
        amqpTemplate.convertAndSend(MakeCardDelayConfig.DELAY_EXCHANGE, MakeCardDelayConfig.DELAY_ROUTING_KEY, orderId, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,
            // 具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1000 * 60 * 5 + "");
            return message;
        });
    }
}
