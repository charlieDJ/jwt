package com.example.boot.common.util;

import redis.clients.jedis.Jedis;

public interface CallWithJedis {
    void call(Jedis jedis);
}
