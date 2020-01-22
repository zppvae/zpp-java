package org.java.sort.test;

import java.util.Arrays;

/**
 * 二分法插入排序
 *
 * 9,3,2,6,10,44,83,28,5,1,0,36
 *
 * 插入元素28：
 *
 *    2    3     6     9     10     44      83
 *    left             mid                  right
 *                           left   mid     right
 *                           right
 *
 *   当left == right时，下一个位置即为元素插入的位置，其他元素依次后移一位
 */
public class BinaryInsertSort {
    public static int[] sort(int[] arr,int length){
        int temp;
        //第一个元素不需要排序
        for (int i = 1; i < length; i++) {
            int left = 0;
            int right = i -1;
            int mid = 0;

            //当前元素
            temp = arr[i];
            while (left <= right) {
                mid = (left + right)/2;
                //当前元素 > 中间元素
                if (temp > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //找到位置，让left右边的元素向后移动一位
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            //若left=i的话，则说明元素不需要挪动位置
            if (left != i) {
                arr[left] = temp;
            }
        }
        return arr;
    }
    public static void main(String[] args){
        int[] arr = {9,3,2,6,10,44,83,28,5,1,0,36};
        System.out.println(Arrays.toString(sort(arr, arr.length)));
    }

}
