package org.java.concurrency.thread.core;

/**
 * 同时使用Runnable和Thread两种实现线程的方式
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                //重写run并且传入Runnable，会调用run()
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
