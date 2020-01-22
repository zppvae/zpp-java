package org.java.jdk8;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalDemo {

    public static void main(String[] args) {
        //不为空输出值，为空输出默认值  "" != 空
//		System.out.println(Optional.ofNullable(" ").orElse("默认值"));

        List<Integer> list = Arrays.asList(1, 2, 3, 2, 5, 3);

        // 集合不为空的时候进行遍历去重,反之输出空集合
        System.out.println(Optional.ofNullable(list).orElse(new ArrayList()).stream().distinct().collect(Collectors.toList()));
    }

}