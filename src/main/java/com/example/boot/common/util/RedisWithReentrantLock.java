package com.example.boot.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author dengjia
 * @date 2019/9/12 10:11
 * <p>
 * 当前线程重入型分布式锁
 */
public class RedisWithReentrantLock {
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * @param key        键
     * @param expireTime 过期时间，秒为单位
     * @return 成功获取锁返回true
     */
    private boolean tryLock(String key, int expireTime) {
        SetParams params = new SetParams();
        params.nx().ex(expireTime);
        return Objects.nonNull(jedis.set(key, "", params));
    }

    private void tryUnLock(String key) {
        jedis.del(key);
        lockers.remove();
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key, int expireTime) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this.tryLock(key, expireTime);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this.tryUnLock(key);
        }
        return true;
    }
}
