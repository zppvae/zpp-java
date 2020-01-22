package org.java.concurrency.thread.lock;

/**
 * 在对象上加锁
 * @author zpp
 *
 */
public class AccountSync3 implements Runnable {
	static AccountSync3 instance = new AccountSync3();
	static int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 10000000; j++) {
			synchronized (instance) {
				i++;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}