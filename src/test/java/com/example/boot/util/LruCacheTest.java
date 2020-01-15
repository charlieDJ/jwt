package com.example.boot.util;

import com.example.boot.common.util.LruCache;

/**
 * @author dengjia on 2020/1/13
 */
public class LruCacheTest {

    public static void main(String[] args) {
        final LruCache<String, Integer> lruCache = new LruCache<>(4);
        lruCache.put("1", 1);
        lruCache.put("2", 2);
        lruCache.put("3", 3);
        lruCache.put("4", 4);
        lruCache.put("5", 5);
        lruCache.forEach((key,value)->{
            System.err.println(key + "-" + value);
        });
    }
}
