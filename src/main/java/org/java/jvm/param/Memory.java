package org.java.jvm.param;

/**
 * -Xmx20m -Xms5m
 * 最大可用内存为20M， 初始内存为5M
 */
public class Memory {
    public static void main(String[] args){
        System.out.print("最大内存");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.print("可用内存");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
        System.out.print("已经使用内存");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
    }
}
