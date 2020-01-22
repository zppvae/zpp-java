package org.java.structure;

import java.util.Arrays;

/**
 * 大于1的自然数中,只能被1和自身整除的数叫质数 
 * @author zpp
 *
 */
public class PrimeNumbers {
	public static void main(String[] args) {
//		int[] arr = getPrimes(10);
//		System.out.println(ArrayRepeatNumber.toString(arr));
		System.out.println(getNextPrime(7));
	}
	
	/**
	 * 获取1~n的质数
	 * @param n
	 * @return
	 */
	public static int[] getPrimes(int n) {
		int[] arr = new int[n];
		//质数为大于1的自然数, 故i从2开始
		for (int i = 2; i < n; i++) {
			boolean isPrime = true;
			//自然数i和大于1小于自己的自然数j作取余运算i%j,若为质数,则i%j不会等于0 
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				arr[i] = i;
			}
		}
		return arr;
	}
	
	/**
	 * 获取当前质数的下一个质数
	 * @param currPrime
	 * @return
	 */
	public static int getNextPrime(int currPrime) {
		int i,j;
		for (i = currPrime+1;;i++) {
			boolean flag = true;
			for (j = 2;j < i;j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return i;
			}
		}
	
	}
	
	
	
	
	
	
	
}
