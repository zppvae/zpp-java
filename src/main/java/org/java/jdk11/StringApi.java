//package org.java.jdk11;
//
///**
// * @author zpp
// * @date 2018/11/14 14:13
// */
//public class StringApi {
//
//    public static void main(String[] args){
//        String s = " Hello, JDK11!\u3000\u3000";
//        System.out.println("     original: [" + s + "]");
//        System.out.println("         trim: [" + s.trim() + "]");
//        //strip()可以去掉Unicode空格
//        System.out.println("        strip: [" + s.strip() + "]");
//        System.out.println(" stripLeading: [" + s.stripLeading() + "]");
//        System.out.println("stripTrailing: [" + s.stripTrailing() + "]");
//
//        String s2 = " \u3000"; // 由一个空格和一个中文空格构成
//        System.out.println(s2.isEmpty()); // false
//        System.out.println(s2.isBlank()); // true
//
//        //按行分割字符串
//        String s3 = "Java\nPython\nRuby";
//        s3.lines().forEach(System.out::println);
//
//        //重复打印
//        System.out.println("1-".repeat(10));
//    }
//}
