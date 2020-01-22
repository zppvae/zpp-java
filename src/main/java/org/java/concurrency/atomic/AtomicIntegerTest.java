package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerTest {

	static AtomicInteger ai = new AtomicInteger(1);

	public static void main(String[] args) {
		log.info("获取自增前的值:{}",ai.getAndIncrement());
		log.info("获取自增后的值:{}",ai.get());
	}

}