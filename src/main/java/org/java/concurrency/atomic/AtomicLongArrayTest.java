package org.java.concurrency.atomic;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArrayTest {
    
    public static void main(String[] args){

        // 新建AtomicLongArray对象
        long[] arrLong = new long[] {10, 20, 30, 40, 50};
        AtomicLongArray ala = new AtomicLongArray(arrLong);

        ala.set(0, 100);
        for (int i=0, len=ala.length(); i<len; i++) 
            System.out.printf("get(%d) : %s\n", i, ala.get(i));

        //100
        System.out.printf("%20s : %s\n", "getAndDecrement(0)", ala.getAndDecrement(0));
        //19
        System.out.printf("%20s : %s\n", "decrementAndGet(1)", ala.decrementAndGet(1));
        //30
        System.out.printf("%20s : %s\n", "getAndIncrement(2)", ala.getAndIncrement(2));
        //41
        System.out.printf("%20s : %s\n", "incrementAndGet(3)", ala.incrementAndGet(3));
        //199
        System.out.printf("%20s : %s\n", "addAndGet(100)", ala.addAndGet(0, 100));
        //19
        System.out.printf("%20s : %s\n", "getAndAdd(100)", ala.getAndAdd(1, 100));
        //true
        System.out.printf("%20s : %s\n", "compareAndSet()", ala.compareAndSet(2, 31, 1000));
        //1000
        System.out.printf("%20s : %s\n", "get(2)", ala.get(2));
    }
}