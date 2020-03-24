package com.example.boot.redis;

import com.example.boot.common.util.RedisHolder;
import com.example.boot.common.util.RedisPool;
import org.junit.Test;

public class HyperLogLogTest {


    @Test
    public void test() {
        final RedisPool pool = new RedisPool();
        final RedisHolder<Long> holder = new RedisHolder<>();
        pool.execute(redis -> {
            for (int i = 0; i < 100_000; i++) {
                redis.pfadd("codehole", "user" + i);
            }
            final long codehole = redis.pfcount("codehole");
            holder.setValue(codehole);
        });

        System.out.println(String.format("期望总数：%d，实际总数：%d", 100_000, holder.getValue()));

    }
}
