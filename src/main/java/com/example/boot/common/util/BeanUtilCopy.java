package com.example.boot.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author dj
 * @date 2021/7/21
 */
public class BeanUtilCopy  {
    /**
     * 复制对象属性到另一个对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    目标对象泛型
     * @param <S>    源对象泛型
     * @return 复制属性后的对象
     */
    public static <S, T> T copyProps(S source, Supplier<T> target) {
        return copyProps(source, target, null);
    }

    /**
     * 复制对象属性到另一个对象，支持个性化设置属性
     *
     * @param source 源对象
     * @param target 目标对象
     * @param copy   回调函数，个性化设置属性
     * @param <T>    目标对象泛型
     * @param <S>    源对象泛型
     * @return 复制属性后的对象
     */
    public static <T, S> T copyProps(S source, Supplier<T> target, BiConsumer<S, T> copy) {
        T t = target.get();
        BeanUtils.copyProperties(source, t);
        if (Objects.nonNull(copy)) {
            copy.accept(source, t);
        }
        return t;
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources: 数据对象集合
     * @param target:  目标对象集合
     * @param copy:    回调函数
     * @return 目标对象集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BiConsumer<S, T> copy) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            BeanUtils.copyProperties(source, t);
            list.add(t);
            if (Objects.nonNull(copy)) {
                copy.accept(source, t);
            }
        }
        return list;
    }

}
