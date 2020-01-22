package org.java.basic;

import java.util.Collections;

public class BasicTest {
	
	public static void main(String[] args) {
//		System.out.println(3*0.1);//浮点数
//        System.out.println(3*0.1==0.3);
//        //float是8位有效数字，第7位数字将会四舍五入
//        float a = 1.32344435f;
//        float b = 3.4f;
//        System.out.println(a);
//
//        double c = 0.0;
//        double d = -0.0;
//        if(Double.doubleToLongBits(c) == Double.doubleToLongBits(d)){
//            System.out.println("相等");
//        }
//
//        float e = 1.1f;
//        float f = 1.11f;
//
//        System.out.println(Float.compare(f, e));

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1.equals(s2));
        System.out.println(s2.hashCode());
	}
}
