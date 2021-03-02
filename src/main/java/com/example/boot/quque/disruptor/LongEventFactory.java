package com.example.boot.quque.disruptor;

import com.example.boot.model.other.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author dj
 * @date 2021/2/24
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
