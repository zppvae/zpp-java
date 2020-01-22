package org.java.concurrency.thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 在这里开启线程进行RealData的call()执行
		Future<String> future = executor.submit(new RealData("a"));
		System.out.println("请求完毕");
		try {
			// 这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// 相当于data.getResult ()，取得call()方法的返回值
		// 如果此时call()方法没有执行完成，则依然会等待
		System.out.println("数据 = " + future.get());
	}
}