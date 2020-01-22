package org.java.concurrency.thread.policy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在线程池当前正在运行的Thread线程池中处理被拒绝的任务
 * Description:
 * @author zpp
 * @date   2018年5月31日
 */
public class CallerRunsPolicyDemo {

    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        // 设置线程池的拒绝策略为"CallerRunsPolicy"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            Runnable myrun = new CallerRunsRunnable("task-"+i);
            pool.execute(myrun);
        }

        // 关闭线程池
        pool.shutdown();
    }
}

class CallerRunsRunnable implements Runnable {
    private String name;
    public CallerRunsRunnable(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " is running.");
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}