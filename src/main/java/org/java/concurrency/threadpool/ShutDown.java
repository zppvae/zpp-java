package org.java.concurrency.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关闭线程池
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        //给线程发出终端信号，抛出InterruptedException异常
        //返回还没有开始执行的任务列表，记录或者重新执行
        List<Runnable> runnableList = executorService.shutdownNow();

        //关闭，已有的任务执行完毕后关闭，此时不会接受新的任务
//        executorService.shutdown();
        //新提交一个任务
//        executorService.execute(new ShutDownTask());
        //awaitTermination()。等待指定时间后线程池是不是已经终止
//        boolean b = executorService.awaitTermination(7L, TimeUnit.SECONDS);
//        System.out.println(b);
        //isShutdown()，是不是已经进入停止状态
//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
        //isTerminated()，线程池是不是已经终止
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());

//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }
}
