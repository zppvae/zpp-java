package org.java.model.singleton;


import org.java.annotations.NotThreadSafe;

/**
 * 懒汉模式
 */
@NotThreadSafe
public class Singleton3 {

    private Singleton3() {}

    private static Singleton3 instance = null;

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
