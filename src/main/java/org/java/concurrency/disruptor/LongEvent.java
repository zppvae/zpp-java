package org.java.concurrency.disruptor;

/**
 * 通过Disruptor 进行交换的数据类型
 */
public class LongEvent {

	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}