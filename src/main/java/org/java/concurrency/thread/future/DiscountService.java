package org.java.concurrency.thread.future;

import java.text.NumberFormat;

import static org.java.concurrency.thread.future.Shop.delay;

/**
 * 折扣服务
 */
public class DiscountService {


	public static String applyDiscount(Quote quote) {
		return quote.getShop() + " price is " + apply(quote.getPrice(), quote.getDiscount());
	}

	private static String apply(double price, Discount discount) {
		delay();
		return NumberFormat.getInstance().format(price * (100 - discount.getPercentage()) / 100);
	}


}