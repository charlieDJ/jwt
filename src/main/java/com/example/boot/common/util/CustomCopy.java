package com.example.boot.common.util;

/**
 * @author dj
 * @date 2021/7/21
 */
@FunctionalInterface
public interface CustomCopy<S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void custom(S t, T s);
}
