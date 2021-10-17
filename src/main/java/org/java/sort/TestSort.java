package org.java.sort;

import org.java.util.SortUtil;

import java.util.Arrays;

public class TestSort {

    public static void main(String[] args) {
        int[] arr = {10, 2, 6, 8, 11, 9, 7, 3, 7, 12};
        int[] a = sort(arr);
        System.out.println(Arrays.toString(a));
    }

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
           int temp = arr[i];
           int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

}
