package com.example.boot.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author dengjia on 2020/1/2
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        MyThread t1 = new MyThread("t1", countDownLatch);
        MyThread t2 = new MyThread("t2", countDownLatch);
        t1.start();
        t2.start();
        System.out.println("main thread await. ");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}


class MyThread extends Thread {
    private CountDownLatch countDownLatch;

    public MyThread(String name, CountDownLatch countDownLatch) {
        super(name);
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " doing something");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}