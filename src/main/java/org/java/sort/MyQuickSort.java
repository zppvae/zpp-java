package org.java.sort;


import org.java.util.SortUtil;

import java.util.Arrays;

public class MyQuickSort {

    public static void main(String[] args) {
        int[] arr = {10, 2, 6, 8, 11, 9, 7, 3, 12};
        sort(arr, 0, arr.length -1);
        System.out.println(Arrays.toString(arr));
    }

    public static int divide(int[] arr, int left, int right) {
        int prviot = left;
        //  与基准值交换的一下个索引
        int index = prviot + 1;
        for (int i = index; i <= right;i++) {
            if (arr[i] < arr[prviot]) {
                SortUtil.swap(arr, i, index);
                index++;
            }
        }
        SortUtil.swap(arr, prviot, index -1);
        // 基准值的下标
        return index - 1;
    }

    public static int[] sort(int[] arr, int left, int right) {
        if (left < right) {
            // 分区索引
            int index = divide(arr, left, right);
            sort(arr, left, index - 1);
            sort(arr, index + 1, right);
        }
        return arr;
    }
}
