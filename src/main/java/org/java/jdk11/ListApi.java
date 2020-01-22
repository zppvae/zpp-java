//package org.java.jdk11;
//
//import java.util.List;
//
///**
// * @author zpp
// * @date 2018/11/14 13:54
// */
//public class ListApi {
//
//    public static void main(String[] args){
//        List<String> list = List.of("tom","john","zpp");
//
//        //旧的转换方式   list转数组
//        String[] oldArr = list.toArray(new String[list.size()]);
//
//        //新的方法:传入IntFunction:
//        String[] newArr = list.toArray(String[]::new);
//
//        System.out.println(oldArr.length);
//        System.out.println(newArr.length);
//
//    }
//}
