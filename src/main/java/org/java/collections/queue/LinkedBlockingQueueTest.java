package org.java.collections.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



public class LinkedBlockingQueueTest {
	
	public static void main(String[] args) {
		JobThread thread = new JobThread();
		thread.start();
		
		try {
			thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}



class JobThread extends Thread {
	private boolean toStop = false;
	private String stopReason;
	private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	public void toStop(String stopReason) {
		/**
		 * Thread.interrupt只支持终止线程的阻塞状态(wait、join、sleep)，
		 * 在阻塞出抛出InterruptedException异常,但是并不会终止运行的线程本身；
		 * 所以需要注意，此处彻底销毁本线程，需要通过共享变量方式；
		 */
		this.toStop = true;
		this.stopReason = stopReason;
	}
	
	@Override
	public void run() {
		while(!toStop){
			try {
				queue.poll(3L, TimeUnit.SECONDS);
				toStop("线程停止");
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(stopReason);
			}
		}
	}
}
