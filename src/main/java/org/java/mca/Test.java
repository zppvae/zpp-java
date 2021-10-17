package org.java.mca;

import org.java.util.SortUtil;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
//        int[] arr = {421, 240, 115, 532, 305, 430, 124};
//
//        SortUtil.print(sort(arr));
        // 此时的123还不在常量池中，在char 数组中
//        String s = new String("123");
//        String s1 = s.intern();
//        System.out.println(s == s1);

        String a=new String("123")+new String("456");
//String b=new String("123456");
        String intern = a.intern();
        System.out.println(intern==a);
    }

    public static int[] sort(int[] arr){
        int[] result = new int[arr.length];
        int[] count = new int[10];
        System.out.println("原数组的元素：" + Arrays.toString(arr));

        for (int i = 0; i < 3; i++) {
            int division = (int)Math.pow(10, i);

            for (int j = 0; j < arr.length; j++) {
                int num = arr[j]/division % 10;
                count[num]++;
            }

            System.out.println("计数后的元素："+Arrays.toString(count));

            for (int m = 1; m < count.length; m++) {
                count[m] = count[m] + count[m-1];
            }

            System.out.println("相加后的元素："+Arrays.toString(count));

            for (int n = arr.length - 1; n >= 0; n--) {
                int num = arr[n]/division % 10;
                result[--count[num]] = arr[n];
            }

            System.arraycopy(result, 0, arr, 0, arr.length);
            Arrays.fill(count, 0);
        }

        return result;

    }

}
