package org.java.concurrency.thread.lock;

/**
 * 错误使用锁的案例
 * @author zpp
 *
 */
public class IntegerLock {
	//Integer不变模式
	static Integer i = 0;//自动拆箱和装箱 ，导致i

	public static class AddThread extends Thread {
		public void run() {
//			synchronized(AddThread.class){
				for (int k = 0; k < 100000; k++) {
					synchronized (i) { //2个线程同步的对象不一定是一样的
						i++;//改变的是i的引用
					}
				}
//			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		AddThread t1 = new AddThread();
		AddThread t2 = new AddThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}