package com.example.boot.util;

import com.example.boot.common.util.RedisHolder;
import com.example.boot.common.util.RedisPool;
import org.junit.Test;

public class JedisTest {

    @Test
    public void setTest() {
        final RedisPool pool = new RedisPool();
        pool.execute(redis -> redis.set("hello", "world"));
    }

    @Test
    public void getTest() {
        final RedisPool pool = new RedisPool();
        RedisHolder<String> holder = new RedisHolder<>();
        pool.execute(redis -> {
            final String helloValue = redis.get("hello");
            holder.setValue(helloValue);
        });
        System.out.println("hello：" + holder.getValue());
    }

}
