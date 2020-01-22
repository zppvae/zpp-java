package org.java.test;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zpp
 * @date 2019/12/18 17:28
 */
public class Child extends Parent {

    public static String test1(){
        try {
            return "1";
        }finally {
            System.out.println("finally");
        }

    }

    public static void main(String[] args) {
        // 6 * 2^3
        System.out.println(6 << 3);
        //48/2^3
        System.out.println(48 >> 3);
    }
}
