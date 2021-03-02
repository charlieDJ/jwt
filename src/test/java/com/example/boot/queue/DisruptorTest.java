package com.example.boot.queue;

import com.example.boot.model.other.LongEvent;
import com.example.boot.quque.disruptor.LongEventFactory;
import com.example.boot.quque.disruptor.LongEventHandler;
import com.example.boot.quque.disruptor.LongEventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author dj
 * @date 2021/2/24
 */
public class DisruptorTest {

    @Test
    public void produce() {
        //new一个消息（事件）工厂
        LongEventFactory factory = new LongEventFactory();
        //设置环形Buffer的SIZE
        int size = 1024;
        //new Disruptor，参数是消息（事件）工厂，Buffer的Size，线程工厂
        Disruptor<LongEvent> longEventDisruptor = new Disruptor<LongEvent>(factory, size, Executors.defaultThreadFactory());
        //设置如何消费生产者产出的消息（事件）
        longEventDisruptor.handleEventsWith(new LongEventHandler());
        //启动--环形Buffer创建成功，所有的位置均已创建好Event对象
        longEventDisruptor.start();
        //获取Disruptor的环形Buffer
        RingBuffer<LongEvent> ringBuffer = longEventDisruptor.getRingBuffer();
        //new 消息（事件）生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        //循环调用-往里添加消息
        for (long l = 0; l < 100; l++) {
            //调用producer的生产消息（事件）的方法
            producer.onData(l, "Log-" + l);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //将消息（事件）发布出去
        longEventDisruptor.shutdown();

    }

}
