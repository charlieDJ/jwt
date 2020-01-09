package com.example.boot.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dengjia on 2019/12/26
 */
public class ScheduleTask {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> System.out.println(111), 3, 10, TimeUnit.SECONDS);
//        executorService.schedule(() -> System.out.println(111), 20, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();
    }
}
