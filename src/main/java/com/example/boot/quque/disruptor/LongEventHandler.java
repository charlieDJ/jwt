package com.example.boot.quque.disruptor;

import com.example.boot.model.other.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author dj
 * @date 2021/2/24
 * 定义消息（事件）的消费方式
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) {
        System.out.println(longEvent.getName() + "-----" + longEvent.getValue());
    }
}

