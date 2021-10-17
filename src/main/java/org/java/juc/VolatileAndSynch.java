package org.java.juc;

/**
 * volatile：只能保证线程之间可见性
 * synchronized：保证线程之间可见性及原子性
 *
 */
public class VolatileAndSynch implements Runnable{

//    volatile int count = 100;

    int count = 100;

    public static void main(String[] args) {
        VolatileAndSynch vs = new VolatileAndSynch();
        for (int i = 0; i < 100; i++) {
            new Thread(vs, "t" + (i+1)).start();
        }
    }

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count: "+count);
    }
}
