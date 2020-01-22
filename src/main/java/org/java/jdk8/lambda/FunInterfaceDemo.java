package org.java.jdk8.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * 函数式接口
 *
 * 接口中只要一个方法
 */
@FunctionalInterface
interface InrerfaceA{
    int get(int i);

    default int add(int a,int b){
        return a + b;
    }
}

public class FunInterfaceDemo {

    public static void printMoney(Function<Integer,String> format,Integer money){
        System.out.println(format.apply(money));
    }

    public static void main(String[] args){
        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#,###").format(i);
        printMoney(integerStringFunction.andThen(s -> "人民币 : "+s),9999999);
    }

}
