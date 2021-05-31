package org.java.model.singleton;

/**
 * 枚举单例（生产环境使用）
 *
 * 为什么使用枚举单例模式最好：
 * 线程安全
 * 枚举类被编译成 final class
 * 避免反序列化破坏单例
 */
public enum Singleton8 {
    INSTANCE;

    public void whatever() {

    }
}
