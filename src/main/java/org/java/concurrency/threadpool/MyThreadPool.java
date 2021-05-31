package org.java.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池扩展
 */
public class MyThreadPool {

    public static void main(String[] args) {
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(2,4,20,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.currentThread().getName();
            });
        }
    }

    static class MyThreadPoolExecutor extends ThreadPoolExecutor{
        /**
         * 保存线程的开始执行时间
         */
        private final ThreadLocal<Long> localTime = new ThreadLocal<>();

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                    TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            Long startTime = System.nanoTime();
            localTime.set(startTime);
            System.out.println(String.format("%s | 开始时间 = %s",t.getName(),startTime));
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            Long endTime = System.nanoTime();
            Long time = endTime - localTime.get();
            System.out.println(String.format("%s | 结束时间 = %s，耗时 = %s",Thread.currentThread().getName()
                    ,endTime,(time)/1000000));
            super.afterExecute(r, t);
        }
    }
}
