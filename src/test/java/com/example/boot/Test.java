package com.example.boot;

import com.google.common.collect.Lists;

/**
 * @author dengjia on 2020/1/2
 */
public class Test {


    public static void main(String[] args) {
        Lists.newArrayList("abc","acb")
                .stream().sorted(String::compareTo)
                .forEachOrdered(System.out::println);
    }

}
