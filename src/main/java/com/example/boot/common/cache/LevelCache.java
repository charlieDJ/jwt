package com.example.boot.common.cache;


import com.example.boot.common.enumeration.Level;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dengjia on 2020/1/9
 */
public class LevelCache {

    private static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(Level.values().length);

    static {
        final Level[] levels = Level.values();
        for (Level level : levels) {
            map.put(level.getCode(), level.getDesc());
        }
    }

    public static String getDesc(int value){
        return map.getOrDefault(value, "未知");
    }


}
