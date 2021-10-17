package org.java.mca;

import java.util.Arrays;

/**
 * 基数排序
 *
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {421, 240, 115, 532, 305, 430, 124};
        // 求元素的最高位数
        int[] result = sort(arr);
        System.out.println("结果-> " + Arrays.toString(result));
    }


    public static int[] sort(int[] arr){
        int[] result = new int[arr.length];
        // 0-9的计数数组
        int[] count = new int[10];

        for (int i = 0; i < 3; i++) {
            // 1、10、100、1000.。。
            int division = (int)Math.pow(10, i);
//            System.out.println(division);

            for (int j = 0; j < arr.length; j++) {
                // 计算元素的个位、十位、百位的值
                int num = arr[j]/division % 10;
//                System.out.println(num + " ");
                // 保存到对应下标的位置中
                count[num] ++;
            }

            System.out.println(division + "-> " +Arrays.toString(count));

            // 计数算法
            for (int m = 1; m < count.length; m++) {
                count[m] = count[m] + count[m-1];
            }

            System.out.println("相加后的元素：" +Arrays.toString(count));

            for (int n = arr.length - 1; n >=0 ; n--) {
                int num = arr[n]/division % 10;
                System.out.println("num-> "+ num);
                int a= --count[num];
                System.out.println("a-> "+ a);
                result[a] = arr[n];
            }

            System.out.println("result-> " + Arrays.toString(result));
            System.arraycopy(result, 0, arr, 0, arr.length);

            // 将count数组重置为0
            Arrays.fill(count, 0);
        }

        return result;
    }
}
