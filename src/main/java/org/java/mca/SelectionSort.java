package org.java.mca;

import java.util.Arrays;

/**
 * 选择排序
 *
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

        for (int i = 0; i < arr.length - 1; i++) {
            // 最小值的位置
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }
            if (minPos != i) {
                int temp = arr[i];
                arr[i] = arr[minPos];
                arr[minPos] = temp;
            }
        }
        for(int tmp : arr) {
            System.out.print(tmp + " ");
        }
    }

    static void sort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            // 最小值的位置
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }
            if (minPos != i) {
                int temp = arr[i];
                arr[i] = arr[minPos];
                arr[minPos] = temp;
            }
        }
    }
}
