package com.example.boot.guava;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author dj
 * @date 2021/3/11
 */
public class StopWatchTest {

    @Test
    public void stop() throws InterruptedException {
        // 创建stopwatch并开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1980);
        // 以秒打印从计时开始至现在的所用时间,向下取整
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS)); // 1
        // 停止计时
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS)); // 1
        // 再次计时
        stopwatch.start();
        Thread.sleep(100);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS)); // 2
        // 重置并开始
        stopwatch.reset().start();
        Thread.sleep(1030);
        // 检查是否运行
        System.out.println(stopwatch.isRunning()); // true
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1031
        System.out.println(millis);
        // 打印
        System.out.println(stopwatch.toString()); // 1.03 s
    }

}
