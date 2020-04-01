package org.java.concurrency.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;


public class PessimismOptimismLock {

    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    /**
     * 悲观锁
     */
    public synchronized void testMethod() {
        a++;
    }


}
