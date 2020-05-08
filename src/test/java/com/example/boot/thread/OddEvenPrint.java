package com.example.boot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dengjia on 2020/4/22
 */
public class OddEvenPrint {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int limit;
    private int initCount;

    public OddEvenPrint(int limit, int initCount) {
        this.limit = limit;
        this.initCount = initCount;
    }

    public void print() {
        try {
            lock.lock();
            while (initCount < limit) {
                System.out.println(String.format("线程【%s】打印数字【%d】", Thread.currentThread().getName(), ++initCount));
                condition.signalAll();
                if (initCount != limit) {
                    condition.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final OddEvenPrint print = new OddEvenPrint(10, 0);
        final Thread thread1 = new Thread(print::print, "thread-A");
        final Thread thread2 = new Thread(print::print, "thread-B");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("打印完毕");

    }


}
