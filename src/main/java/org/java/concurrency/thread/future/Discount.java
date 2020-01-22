package org.java.concurrency.thread.future;

public enum Discount {

	NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

	private final int percentage;

	Discount(int percentage) {
		this.percentage = percentage;
	}

	public int getPercentage() {
		return percentage;
	}
}