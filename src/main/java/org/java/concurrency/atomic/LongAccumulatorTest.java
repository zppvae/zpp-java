package org.java.concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class LongAccumulatorTest {

    public static void main(String[] args) {
        // x + y
        LongAccumulator accumulator = new LongAccumulator((x,y)-> (x * y),1);
        ExecutorService es = Executors.newFixedThreadPool(5);

        IntStream.range(1,10).forEach(i -> es.submit(()->accumulator.accumulate(i)));

        es.shutdown();
        while (!es.isTerminated()){}
        System.out.println(accumulator.getThenReset());
    }
}
