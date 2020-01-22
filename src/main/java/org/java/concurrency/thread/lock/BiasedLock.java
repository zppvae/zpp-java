package org.java.concurrency.thread.lock;

import java.util.List;
import java.util.Vector;

/**
 * 偏向锁
 * 
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 (启用偏向锁) 提升性能
 * -XX:-UseBiasedLocking (禁用偏向锁)
 * @author zpp
 *
 */
public class BiasedLock {
	public static List<Integer> numberList = new Vector<Integer>();

	public static void main(String[] args) throws InterruptedException {
		long begin = System.currentTimeMillis();
		int count = 0;
		int startnum = 0;
		while (count < 10000000) {
			numberList.add(startnum);
			startnum += 2;
			count++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}
