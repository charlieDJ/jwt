package com.example.boot.common.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Slf4j
public class RedisPool {

    private static JedisPool pool;

    // 随容器启动的时候注入到容器中，更合适
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(100);
        config.setMaxIdle(60);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        // 根据配置实例化jedis池
        pool = new JedisPool(config, "127.0.0.1", 16379, 5000, "123456");
    }

    public void execute(CallWithJedis caller) {
        if (pool == null) {
            log.error("jedis 未正常初始化");
            return;
        }
        try (final Jedis jedis = pool.getResource()) {
            caller.call(jedis);
        } catch (JedisConnectionException e) {
            // 重试机制
            try (Jedis jedis = pool.getResource()) {
                caller.call(jedis);
            }
        }
    }

}
