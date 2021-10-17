package org.java.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.Executors;

/**
 * java8写法
 */
public class Main03 {

    public static void main(String[] args) throws Exception{
        int bufferSize = 1024;

        /**
         * 第三个参数：线程工厂
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("event: "+event));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event, sequence, l) -> event.setValue(l), 1000L);
        ringBuffer.publishEvent((event, sequence, l1, l2) -> event.setValue(l1 + l2), 1000L, 200L);
    }
}
