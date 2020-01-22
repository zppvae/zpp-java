package org.java.sort.test;

import java.util.Arrays;

/**
 * 插入排序算法
 *
 *  9,1,3,21,6,8,4,19,11,26
 *
 *  第一轮：9,1,3,21,6,8,4,19,11,26
 *  第二轮：1,9,3,21,6,8,4,19,11,26
 *  第三轮：1,3,9,21,6,8,4,19,11,26
 *
 *  。。。。
 *
 *  第n轮：1,3,4,6,8,9,11,19,21,26
 *
 *  时间复杂度：O（n^2）     1 + 2 + 3...+n
 */
public class InsertSort {

    public static void main(String[] args){
        int[] arr = {9,1,3,5,6,8,4,19,11,26};
        int length = arr.length;
        int temp;
        for (int i = 1; i < length; i++) {
            temp = arr[i];
            int j;
            for (j = i - 1; j >= 0 ; j--) {
                if (arr[j] > temp) {
                    //元素后移一位
                    arr[j + 1] = arr[j];
                } else {
                    //1,3,5,9  x=5
                    //1,3,X,9->
                    break;
                }
            }
            //当遍历到3的时候，发现小于5 break，则5应放到3的下一个位置，即j+1
            arr[j + 1] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }
}
