package org.java.model.singleton;


import org.java.annotations.NotRecommend;
import org.java.annotations.ThreadSafe;

/**
 * 懒汉模式（不推荐使用）
 */
@ThreadSafe
@NotRecommend
public class Singleton4 {

    private Singleton4() {}

    private static Singleton4 instance = null;

    public static synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
