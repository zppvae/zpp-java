package org.java.jvm.param;

/**
 *  -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 *  堆内存初始化值20m,堆内存最大值20m，新生代最大值可用1m，eden空间和from/to空间的比例为2/1
 *
 *  -XX:NewRatio=2    新生代和老年代的占比为1/2
 */
public class SurvivorRatio {

    public static void main(String[] args){
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}
