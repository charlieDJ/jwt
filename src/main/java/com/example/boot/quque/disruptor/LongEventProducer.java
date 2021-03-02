package com.example.boot.quque.disruptor;

import com.example.boot.model.other.LongEvent;
import com.lmax.disruptor.RingBuffer;

/**
 * @author dj
 * @date 2021/2/24
 * 消息（事件）生产者
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long val, String name) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(val);
            event.setName(name);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
