package com.example.boot.other;

/**
 * @author dengjia on 2019/12/13
 */
public class EndRunnable {

    private static class UseRunnable implements Runnable {

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                //如果真发生中断后,这行代码不会执行了
                System.out.println(threadName + " is run!");
            }
            System.out.println(threadName + " interrupt flag is " + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable, "endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }

}

