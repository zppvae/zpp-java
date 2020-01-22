package org.java.jdk8;

/**
 * @author zpp
 * @date 2019/6/6 14:49
 */
public class StringTest {

    public static void main(String[] args){
        //同时会生成堆中的对象 以及常量池中a的对象，但是此时s1是指向堆中的对象的
        String s1 = new String("a");
        //常量池中的已经存在
        s1.intern();
        String s2 = "a";
        System.out.println(s1 == s2);//false

        String s3 = new String("a") + new String("a");
        //jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        s3.intern();
        //jdk1.7之后，常量池中的地址其实就是s3的地址
        String s4 = "aa";
        System.out.println(s3 == s4);//JDK6：false，JDK6+：true
    }
}
