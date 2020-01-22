package org.java.model.singleton;

import org.java.annotations.ThreadSafe;

/**
 * 懒汉式-线程安全
 * @author zpp
 * @date 2019/8/30 16:22
 */
@ThreadSafe
public class SingletonInnerClass {
    //静态私有内部类
    private static class InnerClass {
        private final static SingletonInnerClass instance = new SingletonInnerClass();
    }

    private SingletonInnerClass(){

    }

    public static SingletonInnerClass getInstance(){
        return InnerClass.instance;
    }


    public static void main(String[] args){
        SingletonInnerClass innerClass1 = SingletonInnerClass.getInstance();
        SingletonInnerClass innerClass2 = SingletonInnerClass.getInstance();
        //true
        System.out.println(innerClass1.hashCode() == innerClass2.hashCode());
    }

}
