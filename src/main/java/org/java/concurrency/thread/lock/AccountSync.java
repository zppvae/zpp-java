package org.java.concurrency.thread.lock;

/**
 * 在当前实例上加锁
 * @author zpp
 *
 */
public class AccountSync implements Runnable{
	static AccountSync sync = new AccountSync();
	
	static int i = 0;
	
	public synchronized void increase(){
		i++;
	}
	
	@Override
	public void run() {
		for(int j = 0;j<1000000;j++){
			increase();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(sync);
		Thread t2 = new Thread(sync);
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println(i);
	}
}
