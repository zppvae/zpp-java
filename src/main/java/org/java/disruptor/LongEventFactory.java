package org.java.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * event工厂，用于填充队列
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
