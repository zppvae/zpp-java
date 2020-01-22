package org.java.sort;

import java.util.Arrays;

/**
 * http://mp.weixin.qq.com/s?__biz=MzU0OTE4MzYzMw==&mid=2247484593&idx=1&sn=eee37eef283a5e2cee8fbd53aaf00988&chksm=fbb28d4fccc50459c5292f7a2f3b12af6120533ca83df9b32a7264f1147ea62a3ae98ba468a9&mpshare=1&scene=24&srcid=0323hS05SF0pGwDpWOn5kv4y#rd
 * Description:
 * @author zpp
 * @date   2018年3月25日
 */
public class BasicSort {
	
	public static void main(String[] args) {
		int[] array = {9,6,5,2,3,5,6,8,7,4,0};
		array = shellSort(array);
//		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 交换两个数
	 * @param array
	 * @param a
	 * @param b
	 */
	public static void swap(int[] array,int i,int j) {
		array[i] = array[i] ^ array[j];
		array[j] = array[j] ^ array[i];
		array[i] = array[i] ^ array[j];
		
//		int temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
	}
	
	/**
	 * 冒泡排序
	 * @param array  待排序数组
	 * @param isAsc  是否升序
	 * @return
	 */
	public static int[] bubbleSort(int[] array,boolean isAsc) {
		if (array == null || array.length == 0) {
			return null;
		}
		int length = array.length;
		for (int i = 0;i < length;i++) {
			for (int j = i + 1;j < length;j++) {
				if (isAsc && array[j] < array[i]) {
					swap(array, i, j);
				}
				if (!isAsc && array[j] > array[i]){
					swap(array, i, j);
				}
			}
		}
		return array;
	}
	
	/**
	 * 选择排序（记录每趟排序最小元素的下标）
	 * 
	 * 具体算法描述如下：
		初始状态：无序区为R[1..n]，有序区为空；
		第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
		该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
		n-1趟结束，数组有序化了。
	 * @param array  待排序数组
	 * @param isAsc  是否升序
	 * @return
	 */
	public static int[] selectionSort(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int length = array.length;
		
		for (int i = 0;i < length;i++) {
			int min = i;
			for (int j = i + 1;j < length;j++) {
				if (array[j] < array[min]) {
					min = j;
				}
				
			}
			if (min != i) {
				swap(array, i, min);
			}
			
		}
		return array;
	}
	
	
	/**
	 * 插入排序
	 * 
	 * 具体算法描述如下：
		从第一个元素开始，该元素可以认为已经被排序；
		取出下一个元素，在已经排序的元素序列中从后向前扫描；
		如果该元素（已排序）大于新元素，将该元素移到下一位置；
		重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
		将新元素插入到该位置后；
		重复步骤2~5。
	 * @param array  待排序数组
	 * @param isAsc  是否升序
	 * @return
	 */
	public static int[] insertionSort(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int length = array.length;
		
		int current;
		//length - 1是因为array[i+1]会移动到最后一个元素，从第二个元素开始
		for (int i = 0;i < length - 1;i++) {
			current = array[i+1];//默认第一个排序好，从第二个开始
			int preIndex = i;
			while (preIndex >= 0 && current < array[preIndex]) {
				array[preIndex+1] = array[preIndex];//后移元素
				preIndex --;//向前遍历元素
			}
			
			
			array[preIndex+1] = current;
		}
	
		return array;
	}
	
	
	/**
	 * 希尔排序
	 * 
	 * 具体算法描述：
		选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
		按增量序列个数k，对序列进行k 趟排序；
		每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
	 * @param array  待排序数组
	 * @param isAsc  是否升序
	 * @return
	 */
	public static int[] shellSort(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int length = array.length;
		int temp,gap = length/2;//gap增量序列因子
		
		while (gap > 0) {
			for (int i = gap;i < length;i++) {
				temp = array[i];
				//向前搜索同数组元素
				int preIndex = i - gap;
				while (preIndex >= 0 && array[preIndex] > temp) {
					array[preIndex + gap] = array[preIndex];
					preIndex -= gap;//向前搜索
				}
				
				array[preIndex + gap] = temp;
			}
			
			gap /= 2;
		}
        return array;
	}
	
	public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }

        // 把新数组中的数覆盖a数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }
	
	/**
	 * 归并排序
	 * 
	 * 把长度为n的输入序列分成两个长度为n/2的子序列；
		对这两个子序列分别采用归并排序；
		将两个排序好的子序列合并成一个最终的排序序列。
	 * @param a
	 * @param low
	 * @param high
	 */
    public static void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergeSort(a, low, mid);
            // 右边
            mergeSort(a, mid + 1, high);
            // 左右归并
            merge(a, low, mid, high);
            System.out.println(Arrays.toString(a));
        }

    }
    
    /** 
     * 划分数组元素
     * @param a 
     * @param start 
     * @param end 
     * @return 
     */ 
	public static int devide(int[] a,int start,int end) {
		int base = a[end];//选取最后一个元素为基准元素
		
		while (start < end) {
			while (start < end && a[start] <= base) {
				start++;
			}
			//到此处时就是 a[start] > a[end]
			if (start < end) {
				swap(a, start, end);
				end--;
			}
			while (start < end && a[end] >= base) {
				end--;
			}
			if (start < end) {
				swap(a, start, end);
				start++;
			}
			
		}
		return start;
	}
	
	/**
	 * 快速排序
	 * 选取一个基准元素，基准前面的元素都比基准元素小，后面的都比基准元素大
	 * 然后，递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
	 * @param a
	 * @param start
	 * @param end
	 */
	public static void quickSort(int[] a,int start,int end) {
		
		if (start >= end) {//开始与结束位置相等，结束
			return;
		} else {
			int m = devide(a, start, end);
			quickSort(a, start, m-1);
			quickSort(a, m+1, end);
		}
	}
}
