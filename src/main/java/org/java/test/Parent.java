package org.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zpp
 * @date 2019/12/18 17:26
 */
public class Parent {

    public static String a = "aaa";

    public static List<String> s = new ArrayList<>();


    public static void test(){
        s.add(a);
    }

    public static void main(String[] args) throws Exception{
        test();
        Thread.sleep(10000000);
    }
}
