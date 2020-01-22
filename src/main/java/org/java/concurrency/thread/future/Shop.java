package org.java.concurrency.thread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * https://github.com/jojozhai/zkh-commodity/tree/master/commodity/doc#completablefuture
 */
public class Shop {

	private String name;

	Shop(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void delay() {
		Random random = new Random();
		int delay = 500 + random.nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private double calculatePrice(String product) {
		delay();
		return Math.random() * 100;
	}

//	public Future<Double> getPriceAsync(String product) {
//		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//		new Thread(() -> futurePrice.complete(calculatePrice(product))).start();
//		return futurePrice;
//	}

	/**
	 * 推荐
	 *
	 * 使用 CompletableFuture.supplyAsync 方式来建立 CompletableFuture
	 * supplyAsync 方法内部会对生产者方法产生的异常进行处理，避免你的线程因为异常而永久的阻塞
	 * @param product
	 * @return
	 */
	public Future<Double> getPriceAsync(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	public double getPrice(String product) {
		return calculatePrice(product);
	}

	public String getPriceStr(String product) {
		double price = calculatePrice(product);
		Random r = new Random();
		Discount discount = Discount.values()[r.nextInt(Discount.values().length)];
		return String.format("%s:%.2f:%s", name, price, discount);
	}

	public Quote getPriceQuote(String product) {
		double price = calculatePrice(product);
		Random r = new Random();
		Discount discount = Discount.values()[r.nextInt(Discount.values().length)];
		return Quote.parse(String.format("%s:%.2f:%s", name, price, discount));
	}

	public static void main(String[] args) throws Exception {
		Shop shop = new Shop("BestShop");
		long start = System.currentTimeMillis();
		Future<Double> futurePrice = shop.getPriceAsync("some product");

		//getPriceAsync 方法的调用返回的时间远远早于最终价格计算完成的时间，调用者可以利用这段时间并行的去执行其它操作。
		System.out.println("调用返回,耗时:"+ (System.currentTimeMillis() - start));
		double price = futurePrice.get();
		System.out.println("价格返回,耗时"+ (System.currentTimeMillis() - start));
	}
}