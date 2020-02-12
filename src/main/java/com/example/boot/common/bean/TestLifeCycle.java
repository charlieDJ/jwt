package com.example.boot.common.bean;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

@Service
public class TestLifeCycle implements Lifecycle {

    private volatile boolean running = true;

    @Override
    public void start() {
        System.out.println("Lifecycle==== start");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("Lifecycle==== stop");
        running = true;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

}