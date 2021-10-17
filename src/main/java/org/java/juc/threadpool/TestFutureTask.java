package org.java.juc.threadpool;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask：既是一个任务也是一个Future，可以当做任务来执行然后返回结果
 */
public class TestFutureTask {

    public static void main(String[] args) throws Exception{
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(2);
            return 100;
        });
        new Thread(task).start();
        System.out.println(task.get());
    }
}
