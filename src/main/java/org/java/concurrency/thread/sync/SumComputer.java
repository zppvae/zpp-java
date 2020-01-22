package org.java.concurrency.thread.sync;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 求和计算
 *
 * Stream的iterate方法生成的是装箱的对象，必须拆箱成数字才能求和
 *
 * LongStream：没有装箱拆箱的开销
 *
 * @author zpp
 * @date 2018/12/29 17:26
 */
public class SumComputer {

    /**
     * 并行流计算
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 顺序流计算
     * @param n
     * @return
     */
    public static long sequenceSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum2(long n) {
        return LongStream.rangeClosed(0, n).parallel().reduce(0L, Long::sum);
    }

    public static long sequenceSum2(long n) {
        return LongStream.rangeClosed(0, n).reduce(0L, Long::sum);
    }

    /**
     * 迭代计算
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 性能测试
     * @param computer
     * @param n
     * @return
     */
    public static long test(Function<Long, Long> computer, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            computer.apply(n);
            long duration = System.currentTimeMillis() - start;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static void main(String[] args) {
        long n = 20_000_000;
        System.out.println("顺序归纳耗时:"+test(SumComputer::sequenceSum2, n));
        System.out.println("for循环归纳耗时:"+test(SumComputer::iterativeSum, n));
        System.out.println("并行归纳耗时:"+test(SumComputer::parallelSum2, n));
    }
}
