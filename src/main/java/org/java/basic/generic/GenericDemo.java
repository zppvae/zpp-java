package org.java.basic.generic;

/**
 * 泛型类
 * @param <T>
 */
public class GenericDemo<T>{

    private T key;

    public GenericDemo(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }
}