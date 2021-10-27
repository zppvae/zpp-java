package org.java.sort;

import org.java.util.SortUtil;

import java.util.Arrays;

public class TestSort {

    public static void main(String[] args) {
        int[] arr = {10, 2, 6, 8, 11, 9, 7, 3, 7, 12};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int part = devide(arr, left, right);
            sort(arr, left, part -1);
            sort(arr,part + 1, right);
        }
    }

    public static int devide(int[] arr, int left, int right) {
        int part = left;
        int index = part + 1;

        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[part]) {
                SortUtil.swap(arr, i, index);
                index++;
            }
        }
        SortUtil.swap(arr, part, index - 1);
        return index - 1;
    }
}
