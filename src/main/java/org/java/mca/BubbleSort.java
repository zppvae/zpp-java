package org.java.mca;

import org.java.util.SortUtil;

/**
 * 冒泡
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {7,5,3,1,9,6,4,0};
        sort(arr);
        SortUtil.print(arr);
    }

    public static void sort(int[] arr){
        for (int i = arr.length - 1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    SortUtil.swap(arr, j, j+1);
                }
            }
        }
    }


}
