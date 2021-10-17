package org.java.disruptor;

import lombok.Setter;

/**
 * 环形队列元素
 */
public class LongEvent {

    @Setter
    private long value;

    @Override
    public String toString() {
        return "value: " + value;
    }
}
