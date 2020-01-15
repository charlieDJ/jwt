package com.example.boot.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dengjia on 2020/1/13
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    /**
     * 缓存的大小
     */
    private int cacheSize;

    public LruCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

}
