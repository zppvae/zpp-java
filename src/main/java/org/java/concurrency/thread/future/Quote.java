package org.java.concurrency.thread.future;

import org.apache.commons.lang3.StringUtils;

/**
 * 封装getPriceStr返回的字符串
 */
public class Quote {

	private String shop;
	private double price;
	private Discount discount;

	public String getShop() {
		return shop;
	}

	public double getPrice() {
		return price;
	}

	public Discount getDiscount() {
		return discount;
	}

	public Quote(String shop, double price, Discount discount) {
		this.shop = shop;
		this.price = price;
		this.discount = discount;
	}

	/**
	 * 对shop返回的字符串进行转换
	 * @param content
	 * @return
	 */
	public static Quote parse(String content) {
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(content, ":");
		return new Quote(items[0], Double.parseDouble(items[1]), Discount.valueOf(items[2]));
	}
}