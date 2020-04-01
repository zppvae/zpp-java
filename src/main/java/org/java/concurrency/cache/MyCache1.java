package org.java.concurrency.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述：     最简单的缓存形式：HashMap
 *
 * 1、final关键字，增加安全性
 * 2、synchronized
 */
public class MyCache1 {

    private final HashMap<String,Integer> cache = new HashMap<>();

    public synchronized Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        //先检查HashMap里面有没有保存过之前的计算结果
        if (result == null) {
            //如果缓存中找不到，那么需要现在计算一下结果，并且保存到HashMap中
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        MyCache1 imoocCache1 = new MyCache1();
        System.out.println("开始计算了");
        Integer result = imoocCache1.computer("13");
        System.out.println("第一次计算结果："+result);
        result = imoocCache1.computer("13");
        System.out.println("第二次计算结果："+result);

    }
}
