package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerTest implements Runnable{

	private static final AtomicInteger ai = new AtomicInteger(0);

	private static volatile int basicCount = 0;

	public static void main(String[] args) throws Exception{
		AtomicIntegerTest t = new AtomicIntegerTest();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		log.info("原子变量的值:{}",ai.get());
		log.info("普通变量的值:{}",basicCount);
	}

	public void operate(){
		basicCount++;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			ai.getAndIncrement();
			operate();
		}
	}
}