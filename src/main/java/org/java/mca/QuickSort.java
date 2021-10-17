package org.java.mca;

import org.java.util.SortUtil;

/**
 * 快速排序
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8,32,6,7,2,8,0,9,1,34,6,8};
        sort(arr, 0, arr.length - 1);
        SortUtil.print(arr);
    }

    /**
     *
     * @param arr
     * @param leftBound
     *          左边界
     * @param rightBound
     *          右边界
     */
    public static void sort(int[] arr, int leftBound, int rightBound){
        if (leftBound >= rightBound) {
            // 数组只有一个元素
            return;
        }
        int mid = partition(arr, leftBound, rightBound);
        sort(arr, leftBound, mid - 1);
        sort(arr, mid+1, rightBound);
    }

    /**
     * 数组分区
     *
     * 轴元素左边的元素都比它小，右边的元素都比它大
     *
     * @param arr
     * @param leftBound
     * @param rightBound
     */
    public static int partition(int[] arr, int leftBound, int rightBound){
        // 轴元素为数组最右边的元素
        int pivot = arr[rightBound];
        int left = leftBound;
        // 轴元素前面的一个元素
        int right = rightBound - 1;

        while (left <= right) {
            // left处的元素比轴元素小
            // 往右查找的过程中没有遇到比轴元素大，则继续往右查找
            // left <= right：防止left++超过right
            while (left <= right && arr[left] <= pivot) left++;
            // right处的元素比轴元素大
            // 往左查找的过程中没有遇到比轴元素小，则继续往左查找
            while (left <= right && arr[right] > pivot) right--;

//            System.out.println("交换前：left="+left + ", rgiht="+right);

            // 两个指针未越界
            if (left < right) {
                SortUtil.swap(arr, left, right);
            }

//            SortUtil.print(arr);
        }
        // 此时left > right
        // 将轴元素与左边界进行交换
        SortUtil.swap(arr, left, rightBound);

        // 返回轴元素的位置
        return left;
    }
}
