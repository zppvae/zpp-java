package org.java.juc;


public class Synch {

    static int count = 100;

    static Object o = new Object();

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            mm();
        }
    }

    public static void test(){
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                synchronized (o) {
                    count++;
                    System.out.println(Thread.currentThread().getName() + "获取到了锁，count="+count);
                }
            }).start();
        }
    }

    public synchronized static void m() { //这里等同于synchronized(Synch.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized(Synch.class) { //考虑一下这里写synchronized(this)是否可以？
            count --;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
