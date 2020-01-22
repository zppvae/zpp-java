package org.java.concurrency.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用数组实现的有界阻塞队列
 * @author zpp
 * @date 2019/12/31 14:42
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args){
        //访问者的公平性是使用可重入锁实现的
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10, true);
    }
}
