package org.java.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class Main01 {

    public static void main(String[] args) throws Exception{
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 1024;

        /**
         * 第三个参数：线程工厂
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(1000L);
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
