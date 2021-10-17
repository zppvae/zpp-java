package org.java.mca;


import org.java.util.SortUtil;

/**
 * 归并排序
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 7, 8, 2, 6, 9};

        sort(arr,0,arr.length - 1);

        SortUtil.print(arr);
    }

    public static void sort(int[] arr, int left, int right){
        if (left < 0 || right < 0) {
            throw new RuntimeException("sss");
        }
        if (left == right) {
            return;
        }
        // 分成两半
        int mid = left + (right - left) / 2; // 防止 left +right 越界
        // 左边排序
        sort(arr, left, mid);
        // 右边排序
        sort(arr, mid + 1, right);
        merge(arr, left, mid+1, right);
    }

    /**
     *
     * @param arr
     * @param leftIndex
     *          左指针
     * @param rightIndex
     *          右指针
     * @param rightBound
     *          右边界
     */
    public static void merge(int[] arr, int leftIndex, int rightIndex, int rightBound){
        int mid = rightIndex - 1;
        // 临时数组
        int[] temp = new int[rightBound - leftIndex + 1];

        // 左边数组的开始下标
        int i = leftIndex;
        // 右边数组的下一个位置的下标
        int j = rightIndex;
        // temp数组的下标
        int k = 0;

        // 左右两边数组都没有循环完，有一个数组循环完，则退出while，处理剩下的那个数组的元素
        while (i <= mid && j <= rightBound) {
            // 左边元素 <= 右边的元素
            if (arr[i] <= arr[j]) {
                // 复制左边的元素到临时数组中
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 处理其中一个数组剩下的元素
        // 左边的数组剩下
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //  右边的数组剩下
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }

        for (int l = 0; l < temp.length; l++) {
            arr[leftIndex + l] = temp[l];
        }
    }
}
