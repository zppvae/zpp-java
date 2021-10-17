package org.java.sort;

import org.java.util.SortUtil;

import java.util.Arrays;

/**
 * 选择排序
 */
public class MySelectionSort {

    public static void main(String[] args) {
        int[] arr = {10, 2, 6, 8, 11, 9, 7, 3, 7, 12};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 第几个循环将第几个元素标记为最小元素，然后和后面的元素比较大小
           int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            SortUtil.swap(arr, i, minIndex);
        }
    }
}
