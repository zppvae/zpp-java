package org.java.concurrency.thread.lock;

/**
 * 类上加锁
 * @author zpp
 *
 */
public class AccountSync2 implements Runnable{
	
	static int i = 0;
	
	//静态方法
	public static synchronized void increase(){
		i++;
	}
	
	@Override
	public void run() {
		for(int j = 0;j<1000000;j++){
			increase();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AccountSync2());
		Thread t2 = new Thread(new AccountSync2());
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println(i);
	}
}
