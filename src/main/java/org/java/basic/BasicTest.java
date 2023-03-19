package org.java.basic;

import java.util.Collections;

public class BasicTest {
	
	public static void main(String[] args) {
		System.out.println("3*0.1=" + (3*0.1)); // 0.30000000000000004，浮点数
        System.out.println("3*0.1 == 0.3 " + (3*0.1==0.3)); // false
        System.out.println("4 * 0.1 = " + (4 * 0.1));  // 0.4（0.1 * 2^2）
        System.out.println("4 * 0.1 == 0.4 " + (4 * 0.1 == 0.4)); // true
        //float是8位有效数字，第7位数字将会四舍五入
        float a = 1.32344435f;
        System.out.println(a); // 1.3234444
        // float c = 3.14; //错误，编译通不过，3.14是双精度数，将双精度型（double）赋值给浮点型（float）属于向下转型会造成精度损失, 后面需跟上f或者F转换成float才行。

        double c = 0.0;
        double d = -0.0;
        if(Double.doubleToLongBits(c) == Double.doubleToLongBits(d)){
            System.out.println("相等");
        }
//
//        float e = 1.1f;
//        float f = 1.11f;
//        System.out.println(Float.compare(f, e));

//        String s1 = "abc";
//        String s2 = new String("abc");
//        String s3 = new String("abc");
//        String s4 = "abc";
//        String s5 = "a" + "bc";
//        String s6 = "a";
//        String s7 = "bc";
//        String s8 = s6 + s7;
//        String s9 = (s6 + s7).intern();
//        System.out.println("(s1 equals s2) = " + s1.equals(s2)); // true
//        System.out.println("(s1 equals s3) = " + s1.equals(s3)); // true
//        System.out.println("(s2 equals s3) = " + s2.equals(s3)); // true
//        System.out.println("(s1 equals s5) = " + s1.equals(s5)); // true
//        System.out.println("(s1 equals s8) = " + s1.equals(s8)); // true
//        System.out.println("(s1 equals s9) = " + s1.equals(s9)); // true
//		System.out.println("(s1 == s2) = " + (s1 == s2)); // false
//		System.out.println("(s2 == s3) = " + (s2 == s3)); // false
//        System.out.println("(s1 == s4) = " + (s1 == s4)); // true
//        System.out.println("(s1 == s5) = " + (s1 == s5)); // true
//        System.out.println("(s1 == s8) = " + (s1 == s8)); // false
//        System.out.println("(s1 == s9) = " + (s1 == s9)); // true

        // 当未对vm中的Integercache进行设置的时候其默认值127，cache数组也就是从-128到127
        // 当设置了-XX:AutoBoxCacheMax=1024 参数后，Integercache值就取决于参数得大小
//        Integer a = 1000, b = 1000;
//        Integer c = 100, d = 100;
//        System.out.println("(a == b) = " + (a == b));
//        System.out.println("(c == d) = " + (c == d));
        System.out.println("(0.1 + 0.2) = " + (0.1+0.2)); // 0.30000000000000004, 0.1=1/10, 0.2=1/5
        System.out.println("(0.1f + 0.2f) = " + (0.1f+0.2f)); // 0.3
        System.out.println("(1.1 + 1.2) = " + (1.1 + 1.2));  // 2.3
        System.out.println("(0.5 + 0.25) = " + (0.5+0.25)); // 0.5=1/2（2^1）, 0.25=1/4（2^2）
        System.out.println("(0.1 + 0.2 + 1) = " + (0.1 + 0.2 + 1)); // 1.3 无限循环小数+有限小数=有限小数
	}
}
