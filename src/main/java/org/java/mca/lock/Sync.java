package org.java.mca.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * MarkWord包含的信息
 * - GC的信息（三色标记）
 * - hashcode的信息
 * - 锁的信息（锁升级）
 */
public class Sync {

    private static class T {
        int m;
        String s;
    }

    public static void main(String[] args) throws InterruptedException {
        // 锁升级
//        Thread.sleep(5000);
        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

//        t.hashCode();// 之后再次调用hashcode，直接从对象的markword头获取

        synchronized (t) {
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }

    }
}
