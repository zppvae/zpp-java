package org.java.model.singleton;


import org.java.annotations.NotThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    //A线程执行到A1处时，完成对象初始化，此时B线程执行到B1处，此时instance不为空
    public static SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制        // B1
            synchronized (SingletonExample4.class) { // 同步锁
                //防止多线程同时竞争锁的情况多次创建
                if (instance == null) {
                    instance = new SingletonExample4(); // 3   A1
                }
            }
        }
        return instance;
    }
}
