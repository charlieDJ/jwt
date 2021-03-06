package com.example.boot.other;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dengjia on 2020/1/7
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<>(); //定义一个交换对象，用来交换数据

        //开启一个线程执行任务
        service.execute(() -> {
            try {
                String data1 = "海洛因";
                System.out.println("线程" + Thread.currentThread().getName()
                        + "正在把毒品" + data1 + "拿出来");
                Thread.sleep((long) (Math.random() * 10000));

                //把要交换的数据传到exchange方法中，然后被阻塞，等待另一个线程与之交换。返回交换后的数据
                String data2 = exchanger.exchange(data1);

                System.out.println("线程" + Thread.currentThread().getName() +
                        "用海洛因换来了" + data2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
                System.out.println("交易完毕，拿着钱快跑！");
            }
        });

        //开启另一个线程执行任务
        service.execute(() -> {
            try {
                String data1 = "300万";
                System.out.println("线程" + Thread.currentThread().getName() +
                        "正在把" + data1 + "拿出来");
                Thread.sleep((long) (Math.random() * 10000));

                String data2 =  exchanger.exchange(data1);

                System.out.println("线程" + Thread.currentThread().getName() +
                        "用300万弄到了" + data2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
                System.out.println("交易完毕，拿着海洛因快跑！");
            }
        });
    }
}