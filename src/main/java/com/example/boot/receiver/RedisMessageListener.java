package com.example.boot.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author dengjia on 2019/12/4
 */
@Component
@Slf4j
public class RedisMessageListener implements MessageListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info(String.valueOf(message));
        String keyStr = new String(message.getBody());
        log.info("receive redis expire message" + keyStr);
        log.info("receive time: {}", LocalDateTime.now().toString());
    }
}
