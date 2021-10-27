package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 高并发场景下，LongAddr 比 AtomicLong 性能更好
 */
@Slf4j
public class LongAdderTest {

    public static void main(String[] args) throws Exception{
        LongAdder al = new LongAdder();
        ExecutorService es = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            es.submit(new Task(al));
        }

        es.shutdown();
        while (!es.isTerminated()) {

        }
        long end = System.currentTimeMillis();
        log.info("--{}",al.sum());
        al.add(1L);
        log.info("---" + al.sum());
        log.info("耗时：{}",(end - start));
    }

    static class Task implements Runnable{
        private LongAdder counter;

        public Task(LongAdder counter){
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
