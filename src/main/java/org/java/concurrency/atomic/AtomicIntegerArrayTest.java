package org.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerArray;

@Slf4j
public class AtomicIntegerArrayTest {

	static int[] value = new int[] { 1, 2 };

	static AtomicIntegerArray ai = new AtomicIntegerArray(value);

	public static void main(String[] args) {
		ai.getAndSet(0, 3);
		log.info("索引[{}]的值:{}",0,ai.get(0));
		log.info("原数组索引[{}]的值:{}",0,value[0]);
	}

}