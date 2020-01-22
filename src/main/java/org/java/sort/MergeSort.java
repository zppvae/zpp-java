package org.java.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 归并排序
 * Description:
 * @author zpp
 * @date   2018年3月31日
 */
@Slf4j
public class MergeSort {
	
	public static void main(String[] args) {
		int[] array = {12,3,5,26,68,57,57,24,30,-10,-2};
//		array = shellSort(array, false);
		//辅助数组
		int[] tmp = new int[array.length];
		mergeSort(array,tmp, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void mergeSort(int[] a,int[] tmp,int start,int end) {
		int mid = (start + end)/2;
		
		if (start < end) {
			//左边数组
			mergeSort(a, tmp,start, mid);
			//右边数组
			mergeSort(a,tmp,mid + 1, end);
			merge(a, tmp,start, mid, end);
			
		}
	}
	
	public static void merge(int[] a,int[] tmp,int left,int mid,int right) {
		//p1、p2是检测指针，k是存放指针
		int p1 = left, p2 = mid + 1, k = left;

		while (p1 <= mid && p2 <= right) {
			if (a[p1] <= a[p2]){
				tmp[k++] = a[p1++];
			} else {
				tmp[k++] = a[p2++];
			}

		}

		while (p1 <= mid) {
			//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
			tmp[k++] = a[p1++];
		}
		while (p2 <= right) {
			tmp[k++] = a[p2++];
		}

		//复制回原素组
		for (int i = left; i <= right; i++) {
			a[i] = tmp[i];
		}
		log.info("left: {},right：{},{}",left,right,Arrays.toString(a));
	}
}
