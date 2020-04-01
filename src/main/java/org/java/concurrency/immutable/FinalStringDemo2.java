package org.java.concurrency.immutable;


public class FinalStringDemo2 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashixiong();//通过方法获得，编译器不能提前知道
        String c = b + 2;//c在运行时才知道具体的值
        System.out.println(a == c);

    }

    private static String getDashixiong() {
        return "wukong";
    }
}
