package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;
import org.java.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterTest implements Runnable{

    private static AtomicIntegerFieldUpdater<Candidate> updater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "count");

    static Candidate c1;
    static Candidate c2;

    public static class Candidate{
        volatile int count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            c1.count++;
            updater.getAndIncrement(c2);
        }
    }

    public static void main(String[] args) throws Exception{
        c1 = new Candidate();
        c2 = new Candidate();
        AtomicIntegerFieldUpdaterTest t = new AtomicIntegerFieldUpdaterTest();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("普通变量：{}",c1.count);
        log.info("升级变量：{}",c2.count);
    }
}
