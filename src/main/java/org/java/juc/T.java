package org.java.juc;

import cn.hutool.core.thread.ThreadUtil;

public class T {

    static int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

    static String[] chars = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static void main(String[] args) throws InterruptedException {
        final Object o = new Object();
        Thread t1 = new Thread(()-> {
            synchronized (o) {
                for (int i = 0; i < chars.length; i++) {
                    try {
                        System.out.println(chars[i]);
                        // 释放锁，让t2执行
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 通知t2执行
                    o.notify();
                }
            }

        },"t1");
        t1.start();

        ThreadUtil.sleep(500);

        Thread t2 = new Thread(()-> {
            synchronized (o) {
                for (int i = 0; i < numbers.length; i++) {
                    try {
                        System.out.println(numbers[i]);
                        // 通知t1执行
                        o.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        // 释放锁，进行等待
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        },"t2");
        t2.start();

        t1.join();
        t2.join();
    }

}
