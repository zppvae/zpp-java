package org.java.model.singleton;


import org.java.annotations.ThreadSafe;

/**
 * 饿汉模式（静态代码块）
 */
@ThreadSafe
public class Singleton2 {

    // 私有构造函数
    private Singleton2() {

    }

    // 单例对象
    //A、B代码前后顺序对结果会影响instance的值
    //A
    private static  final Singleton2 instance;

    //B
    static {
        instance = new Singleton2();
    }

    // 静态的工厂方法
    public static Singleton2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
