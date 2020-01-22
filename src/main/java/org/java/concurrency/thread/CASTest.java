package org.java.concurrency.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 线程1负责将100 —> 110 —> 100，线程2执行 100 —>120
 *
 * AtomicStampedReference  带有时间戳的对象引用，解决ABA问题
 * @author zpp
 *
 */
public class CASTest {
	private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);

    public static void main(String[] args) throws InterruptedException {

        //AtomicInteger
        Thread at1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前值："+atomicInteger.get());
                //如果当前状态值等于预期值，则以原子方式将同步状态设置为给定的更新值
                atomicInteger.compareAndSet(100,110);
                atomicInteger.compareAndSet(110,100);
                System.out.println(Thread.currentThread().getName()+" 执行完成");
            }
        });

        Thread at2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);      // at1,执行完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //初始值已经被改过
                System.out.println("AtomicInteger:" + atomicInteger.compareAndSet(100,120));
            }
        });

        at1.start();
        at2.start();

        at1.join();
        at2.join();

        Thread tsf1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //让 tsf2先获取stamp，导致预期时间戳不一致
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 当前值：" + atomicStampedReference.getReference()+",stamp："+atomicStampedReference.getStamp());
                // 预期引用：100，更新后的引用：110，预期标识getStamp() 更新后的标识getStamp() + 1
                atomicStampedReference.compareAndSet(100,110,
                        atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
                atomicStampedReference.compareAndSet(110,100,
                        atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            }
        });

        Thread tsf2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();

                try {
                    TimeUnit.SECONDS.sleep(2);      //线程tsf1执行完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 当前值：" + atomicStampedReference.getReference()+",stamp："+stamp);
                System.out.println("AtomicStampedReference:"
                        +atomicStampedReference.compareAndSet(100,120,
                        stamp,stamp + 1));

                System.out.println(Thread.currentThread().getName() + " 修改后的值：" + atomicStampedReference.getReference()+",stamp："+stamp);
            }
        });

        tsf1.start();
        tsf2.start();
    }
}
