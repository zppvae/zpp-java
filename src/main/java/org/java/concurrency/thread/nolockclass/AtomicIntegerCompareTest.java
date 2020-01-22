package org.java.concurrency.thread.nolockclass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个synchronized的方法和一个AtomicInteger的方法来进行测试
 * @author zpp
 *
 */
public class AtomicIntegerCompareTest {
	private int value;

	public AtomicIntegerCompareTest(int value) {
		this.value = value;
	}

	public synchronized int increase() {
		return value++;
	}

	public static void main(String args[]) {
		long start = System.currentTimeMillis();

		AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
		for (int i = 0; i < 1000000; i++) {
			test.increase();
		}
		long end = System.currentTimeMillis();
		System.out.println("time elapse:" + (end - start));

		long start1 = System.currentTimeMillis();

		AtomicInteger atomic = new AtomicInteger(0);

		for (int i = 0; i < 1000000; i++) {
			atomic.incrementAndGet();
		}
		long end1 = System.currentTimeMillis();
		System.out.println("time elapse:" + (end1 - start1));

	}
}