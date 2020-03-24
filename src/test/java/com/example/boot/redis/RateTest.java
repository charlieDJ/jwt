package com.example.boot.redis;

import com.example.boot.common.util.FunnelRateLimiter;
import com.example.boot.common.util.SimpleRateLimiter;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RateTest {

    private JedisPool pool;


    @Test
    public void test() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(100);
        config.setMaxIdle(60);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        // 根据配置实例化jedis池
        JedisPool pool = new JedisPool(config, "127.0.0.1", 16379, 5000, "123456");
        SimpleRateLimiter limiter = new SimpleRateLimiter(pool.getResource());
        for (int i = 0; i < 20; i++) {
            System.out.println(limiter.isActionAllowed("dj", "reply", 60, 5));
        }
    }

    @Test
    public void funnelTest(){
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(100);
        config.setMaxIdle(60);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        // 根据配置实例化jedis池
        JedisPool pool = new JedisPool(config, "127.0.0.1", 16379, 5000, "123456");
        final FunnelRateLimiter limiter = new FunnelRateLimiter();
        for (int i = 0; i < 20; i++) {
            System.out.println(limiter.isActionAllowed("dj", "reply", 5, 5));
        }
    }




}
