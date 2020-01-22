package org.java.sort;

/**
 * @author zpp
 * @date 2019/7/29 15:18
 */
public class SortUtil {
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
