package com.example.boot.other;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLock {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(MyTask::new);
        executor.submit(MyTask::new);
    }

    public static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("complete");
            return "complete";
        }
    }
}
