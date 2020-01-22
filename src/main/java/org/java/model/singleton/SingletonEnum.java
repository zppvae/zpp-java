package org.java.model.singleton;


import org.java.annotations.Recommend;
import org.java.annotations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonEnum {

    // 私有构造函数
    private SingletonEnum() {

    }

    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonEnum singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonEnum();
        }

        public SingletonEnum getInstance() {
            return singleton;
        }
    }
}
