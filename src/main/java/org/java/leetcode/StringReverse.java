package org.java.leetcode;

/**
 * 字符串反转
 *
 */
public class StringReverse {
    public static void main(String[] args) {
        String str = "abcdefgh123";
        System.out.println("method 1: "+ method1(str));
        System.out.println("method 2: "+ method2(str));
        System.out.println("method 3: "+ method3(str));

    }

    public static String method1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String method2(String str) {
        char[] chars = str.toCharArray();
        String result = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            result = result + chars[i];
        }
        return result;
    }

    public static String method3(String str) {
        String result = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            result = str.charAt(i) + result;
        }
        return result;
    }
}
