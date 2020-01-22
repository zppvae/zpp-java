package org.java.sort.test;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args){
        int[] arr = {10,12,4,6,2,23,9,8,1,11};
        System.out.println(Arrays.toString(selectSort(arr, arr.length)));
    }

    /**
     * 选择排序
     * 10,12,4,6,2,23,9,8,1,11
     *
     * 1,12,4,6,2,23,9,8,10,11
     * 1,2,4,6,12,23,9,8,10,11
     * ...
     * ...
     * ...
     * 1,2,4,6,8,9,10,11,12,23
     * @param arr
     * @param length
     * @return
     */
    public static int[] selectSort(int[] arr,int length){
        int minIndex;

        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }
}
