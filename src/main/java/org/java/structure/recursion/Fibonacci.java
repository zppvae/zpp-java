package org.java.structure.recursion;

import java.math.BigInteger;

/**
 * 斐波那契数列 Description:
 * 
 * @author zpp
 * @date 2018年6月19日
 */
public class Fibonacci {

	/**
	 * 0 ，1 ，1 ，2 ，3 ，5 ，8，13 ，21 ，34 ，55 ，89 ，…
	 * 
	 * @param day
	 * @return
	 */
	public static int fib(int day) {
		if (day == 0) {
			return 0;
		}
		if (day == 1 || day == 2) {
			return 1;
		}

		return fib(day - 1) + fib(day - 2);
	}

	// BigInteger可以防止数据异常
	// BigInteger 任意大的整数，原则上是，只要你的计算机的内存足够大，可以有无限位的
	// 递推实现方式（迭代的方式效率高，时间复杂度O(n)）
	public static BigInteger fibonacciN(int n) {
		if (n == 1) {
			return new BigInteger("0");
		}
		// f(0)=0;
		BigInteger n1 = new BigInteger("0");
		// f(1)=1;
		BigInteger n2 = new BigInteger("1");
		// 记录最终值f(n)
		BigInteger sn = new BigInteger("0");
		for (int i = 0; i < n - 1; i++) {
			sn = n1.add(n2);// 相加
			n1 = n2;
			n2 = sn;
		}
		return sn;
	}

	public static void main(String[] args) {
		System.out.println(fib(20));
		System.out.println(fibonacciN(20));
	}
}
