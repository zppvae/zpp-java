package org.java.concurrency.cache;

import org.java.concurrency.cache.computable.Computable;
import org.java.concurrency.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * 用装饰者模式，给计算器自动添加缓存功能
 */
public class MyCache2<A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap();

    private  final Computable<A,V> c;

    public MyCache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        //包装原有的类
        V result = cache.get(arg);
        if (result == null) {
            //装饰额外的功能
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MyCache2<String, Integer> expensiveComputer = new MyCache2<>(
                new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果："+result);
        result = expensiveComputer.compute("13");
        System.out.println("第二次计算结果："+result);
    }
}
