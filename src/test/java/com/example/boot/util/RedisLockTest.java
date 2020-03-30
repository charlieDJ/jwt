package com.example.boot.util;

import com.example.boot.common.util.RedisWithReentrantLock;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CompletableFuture;

/**
 * @author dengjia on 2020/1/15
 */
public class RedisLockTest {
    public static void main(String[] args) throws InterruptedException {
        String key = "codehole";
        Jedis jedis = new Jedis();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codehole", 5));
        CompletableFuture.runAsync(() -> System.out.println("异步线程是否获取锁："+ redis.lock("codehole", 5)));
       /* System.out.println(redis.lock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));*/
        System.out.println("未超时的value: " + jedis.get(key));
        Thread.sleep(5000);
        System.out.println("超时后的value: " + jedis.get(key));
    }
}
