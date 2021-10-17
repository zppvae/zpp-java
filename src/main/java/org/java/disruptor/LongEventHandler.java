package org.java.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public static long count = 0;

    /**
     *
     * @param longEvent
     * @param sequence
     *          序号
     * @param endOfBatch
     *          是否是最后一个元素
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("[ " + Thread.currentThread().getName() + "] " + longEvent + "，序号："+sequence + "，是否结尾：" +endOfBatch);
    }
}
