package org.java.mca;

import org.java.util.SortUtil;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 2, 3, 7, 1, 1, 3, 5, 5, 6, 9, 8, 5, 7, 4, 2, 9, 4};
        int[] result = sort(arr);
        System.out.println("结果值："+Arrays.toString(result));
    }

    public static int[] sort(int[] arr){
        // 结果数组
        int[] result = new int[arr.length];

        // 取值范围0-9
        int[] count = new int[10];

        System.out.println("原数组："+Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            // 将对应数组元素的再新数组中的位置的数量+1，统计元素出现的次数
            count[arr[i]]++;
        }

        System.out.println("统计后："+Arrays.toString(count));

//        for (int i = 0,j=0; i < count.length; i++) {
//            while (count[i]-- > 0) {
//                result[j++] = i;
//            }
//        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        System.out.println("相加后："+Arrays.toString(count));

        for (int i = arr.length - 1; i >=0 ; i--) {
            // 对下标所在的计数值减1
            int a = --count[arr[i]];
            result[a] = arr[i];
        }
        return result;
    }




}
