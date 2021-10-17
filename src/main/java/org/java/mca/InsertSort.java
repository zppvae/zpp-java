package org.java.mca;

import org.java.util.SortUtil;

/**
 * 插入排序
 *
 * 最有用
 *
 * 从索引为1的下标开始，索引为0的默认有序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {8,32,6,7,2,8,0,9,1};
        sort(arr);
        SortUtil.print(arr);
    }

    public static void sort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SortUtil.swap(arr, j,j - 1);
                }
            }
        }
    }
}
