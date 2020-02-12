package com.example.boot;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengjia on 2020/1/2
 */
public class Test {


    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        int c = -1;
        c = count.getAndIncrement();
        System.err.println("c的容量：" + c);
    }

}
