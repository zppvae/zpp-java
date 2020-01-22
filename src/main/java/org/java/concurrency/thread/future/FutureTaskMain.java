package org.java.concurrency.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 
 * @author zpp
 *
 */
@Slf4j
public class FutureTaskMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("a"));

		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1));
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 在这里开启线程进行RealData的call()执行
		pool.submit(future);

		System.out.println("请求完毕");
		try {
			// 这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// 相当于data.getResult ()，取得call()方法的返回值
		// 如果此时call()方法没有执行完成，则依然会等待
		System.out.println("数据 = " + future.get());

//		if (future.isDone()) {
//			log.info("future task 执行结束");
//		}
	}
}