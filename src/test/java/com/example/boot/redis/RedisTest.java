package com.example.boot.redis;

import com.example.boot.JwtApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author dengjia on 2019/12/4
 */
@Slf4j
public class RedisTest extends JwtApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void expireTest() throws InterruptedException {
        redisTemplate.expire("expire", 5, TimeUnit.SECONDS);
        log.info("send time: {}" + LocalDateTime.now().toString());
        TimeUnit.SECONDS.sleep(2);
    }

}
