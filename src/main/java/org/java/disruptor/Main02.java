package org.java.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.Executors;

public class Main02 {

    public static void main(String[] args) throws Exception{
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 1024;

        /**
         * 第三个参数：线程工厂
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        EventTranslator<LongEvent> translator1 = new EventTranslator<LongEvent>() {
            @Override
            public void translateTo(LongEvent longEvent, long l) {
                longEvent.setValue(1100L);
            }
        };

        ringBuffer.publishEvent(translator1);

        /**
         * 1个参数
         */
        EventTranslatorOneArg<LongEvent,Long> translator2 = new EventTranslatorOneArg<LongEvent,Long>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Long l) {
                event.setValue(l);
            }
        };

        ringBuffer.publishEvent(translator2, 1200L);

        /**
         * 2个参数
         */
        EventTranslatorTwoArg<LongEvent, Long, Long> translator3 = new EventTranslatorTwoArg<LongEvent, Long, Long>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Long l1, Long l2) {
                event.setValue(l1 + l2);
            }
        };

        ringBuffer.publishEvent(translator3, 10000L, 10000L);

        /**
         * 3个参数
         */
        EventTranslatorThreeArg<LongEvent, Long, Long, Long> translator4 = new EventTranslatorThreeArg<LongEvent, Long, Long, Long>() {
            @Override
            public void translateTo(LongEvent event, long sequence, Long l1, Long l2, Long l3) {
                event.setValue(l1 + l2 + l3);
            }
        };

        ringBuffer.publishEvent(translator4, 2000L, 3000L, 4000L);

        /**
         * 多个参数
         */
        EventTranslatorVararg<LongEvent> translator5 = new EventTranslatorVararg<LongEvent>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Object... objects) {
                long result = 0;
                for(Object o : objects) {
                    long l = (Long)o;
                    result += l;
                }
                event.setValue(result);
            }
        };

        ringBuffer.publishEvent(translator5, 1000L, 2000L, 3000L, 4000L, 5000L, 6000L);
    }
}
