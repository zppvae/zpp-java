package org.java.util;

/**
 * @author zpp
 * @date 2018/10/24 10:59
 */
public class IntegerUtils {

    /**
     * 交换两个位置的元素
     * @param nums
     * @param i
     * @param j
     */
    public static void swap (int[] nums,int i,int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args){
        Integer a = 123;
        Integer b = 123;
        Integer c = Integer.valueOf(123);
        Integer d = new Integer(123);
        Integer e = new Integer(123);
        System.out.println(a == b);
        System.out.println(c == a);
        System.out.println(d == e);
        System.out.println(d == c);
    }
}
