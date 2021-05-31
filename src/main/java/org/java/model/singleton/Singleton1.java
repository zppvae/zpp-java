package org.java.model.singleton;


import org.java.annotations.ThreadSafe;

/**
 * 饿汉模式（静态常量）
 */
@ThreadSafe
public class Singleton1 {

    // 私有构造函数
    private Singleton1() {

    }

    // 单例对象
    private static final Singleton1 instance = new Singleton1();

    // 静态的工厂方法
    public static Singleton1 getInstance() {
        return instance;
    }
}
