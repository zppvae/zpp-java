package org.java.concurrency.immutable;


public class FinalStringDemo1 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";//常量，访问b的时候直接返回常量
        String d = "wukong";
        String c = b + 2;//c、a指向同一个引用，同一个常量
        String e = d + 2;//编译器在运行时才知道d的值，e在堆上
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
