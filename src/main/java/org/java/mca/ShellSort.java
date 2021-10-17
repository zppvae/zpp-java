package org.java.mca;

import org.java.util.SortUtil;

/**
 * 希尔排序
 *
 * gap规则：Knuth序列（h = 3h+1）
 *
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {9, 6, 11, 3, 5, 12, 8, 7, 10, 15, 14, 4, 1, 13, 2};
        sort(arr);
        SortUtil.print(arr);
    }

    public static void sort(int[] arr){
        // h 不能大于等于整个数组长度的1/3，否则没有意义
        int h = 1;
        while (h <= arr.length/3) {
            h = h*3 + 1;
        }
        for (int gap = h; gap > 0 ; gap = (gap-1)/3) {
            // i++的原因：gap后面的元素也要进行插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1 ; j-=gap) {
                    if (arr[j] < arr[j-gap]) {
                        SortUtil.swap(arr, j, j-gap);
                    }
                }
            }
        }
    }
}
