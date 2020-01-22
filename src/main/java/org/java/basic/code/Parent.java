package org.java.basic.code;

public class Parent {

    {
        System.out.println("父类普通代码块");
    }

    //多个对象只会初始化一次
    static {
        System.out.println("父类静态代码块");
    }

    public Parent(){
        System.out.println("父类构造方法");
    }
}
