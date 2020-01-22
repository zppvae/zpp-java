package org.java.basic.code;

public class Child extends Parent{

    {
        System.out.println("子类普通代码块");
    }

    //多个对象只会初始化一次
    static {
        System.out.println("子类静态代码块");
    }

    public Child(){
        System.out.println("子类构造方法");
    }

    public static void main(String[] args){
        Child c1 = new Child();
    }
}
