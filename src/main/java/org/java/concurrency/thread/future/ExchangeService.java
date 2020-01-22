package org.java.concurrency.thread.future;

/**
 * 汇率
 */
public class ExchangeService {

	public static double getRate(String source, String target) {
		delay();
		return 10;
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}