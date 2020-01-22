package org.java.concurrency.thread.scheduling;

/**
 * 线程调度     让步
 * 
 * 线程的让步使用Thread.yield()方法，yield()为静态方法，功能是暂停当前正在执行的线程对象，并执行其他线程。
 * Description:
 * @author zpp
 * @date   2017年8月8日
 */
public class ThreadYield {
    public static void main(String[] args) {
        ThreadYield threadYield = new ThreadYield();
        Thread t1 = threadYield.new MyThread1();
        Thread t2 = new Thread(threadYield.new MyRunnable());

        t2.start();
        t1.start();
    }

    class MyThread1 extends Thread {
        public void run() {
            for (int i = 1; i < 10; i++) {
                System.out.println("线程1第" + i + "次执行！");
            }
        }
    }

    class MyRunnable implements Runnable {
        public void run() {
            for (int i = 1; i < 10; i++) {
                System.out.println("线程2第" + i + "次执行！");
                Thread.yield();
            }
        }
    }
}