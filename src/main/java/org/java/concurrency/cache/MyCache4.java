package org.java.concurrency.cache;

import org.java.concurrency.cache.computable.Computable;
import org.java.concurrency.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * 缩小了synchronized的粒度，提高性能，但是依然并发不安全
 *
 * synchronized
 */
public class MyCache4<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap();

    private final Computable<A, V> c;

    public MyCache4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            synchronized (this) {
                cache.put(arg, result);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MyCache4<String, Integer> expensiveComputer = new MyCache4<>(
                new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果：" + result);
        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果：" + result);
    }
}
