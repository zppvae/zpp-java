package org.java.sort.test;

import org.java.util.SortUtil;

/**
 * 希尔排序（增量）  不稳定排序
 */
public class HeerSort {
    //希尔排序
    public static void main(String[] args) {
        int[] a = {100, 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, -1, 12, 64, 1, 33, 85, 29, 10, -8};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //希尔排序
        System.out.println();
        sort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 选择一个间隔，间隔依次递减，一直到1
     * @param a
     */
    public static void sort(int[] a) {
        int d = a.length / 2;
        while (true) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < a.length; j += d) {
                    if (a[j] > a[j + d]) {
                        SortUtil.swap(a, j, j+d);
                    }
                }
            }
            if (d == 1) {
                break;
            }
            //d=d/2;//结果不正确，所以为不稳定排序
            d--;
        }
    }
}
