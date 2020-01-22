package org.java.concurrency.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	private static CountDownLatch cdl = new CountDownLatch(5);
	
	public static void main(String[] args) {
		new Boss().start();
		
		for (int i = 0;i < cdl.getCount();i++) {
			new Employee().start();
		}
	}
	
	static class Boss extends Thread{
		@Override
		public void run() {
			System.out.println("Boss 到达会议室。。。");
			try {
				cdl.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("所有人都已经到齐了，开会吧...");
		}
	}
	
	static class Employee extends Thread{
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"到达会议室。。。");
			cdl.countDown();
		}
	}
}
