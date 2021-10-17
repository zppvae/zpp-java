package org.java.juc;

public class YieldAndJoin {

    public static void main(String[] args) {
//        testYield();
        testJoin();
    }

    public static void testYield(){
        new Thread(()->{
            for (int i=0;i<100;i++) {
                if (i % 10 == 0)  {
                    System.out.println("t1执行 yield");
                    Thread.yield();
                }
            }
        },"t1").start();
        new Thread(()->{
            for (int i=0;i<100;i++) {
                if (i % 10 == 0)  {
                    System.out.println("t2执行 yield");
                    Thread.yield();
                }
            }
        },"t2").start();
    }

    public static void testJoin(){
        Thread t1 = new Thread(()->{
            System.out.println("t1 开始执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("t2 等待 t1 执行完毕后开始执行");
                t1.join();
                System.out.println("t2 开始执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");

        t1.start();
        t2.start();
        System.out.println("main 线程等待 t1 t2执行完成");
    }
}
