package org.java.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  实现CompletionStage接口（40余个方法）
 *  	Java 8中对Future的增强版
 *  	支持流式调用
 *     https://www.jianshu.com/p/6f3ee90ab7d3
 * @author zpp
 *
 */
public class CompletableFutureDemo {
	public static class AskThread implements Runnable {
		CompletableFuture<Integer> re = null;

		public AskThread(CompletableFuture<Integer> re) {
			this.re = re;
		}

		@Override
		public void run() {
			int myRe = 0;
			try {
				myRe = re.get() * re.get();
			} catch (Exception e) {
			}
			System.out.println("计算结果："+myRe);
		}
	}

	public static void testAskThread() throws Exception{
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		new Thread(new AskThread(future)).start();
		// 模拟长时间的计算过程
		Thread.sleep(1000);
		// 告知完成结果
		future.complete(60);
	}

	public static Integer calc(Integer i) {
		try {
			if (i == 1) {
				Thread.sleep(3000);//任务1耗时3秒
			} else if (i == 5) {
				Thread.sleep(5000);//任务5耗时5秒
			} else {
				Thread.sleep(1000);//其它任务耗时1秒
			}
			System.out.println("task线程：" + Thread.currentThread().getName()
					+ "任务i=" + i + ",完成！+" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * allOf是等待所有任务完成，构造后CompletableFuture完成
	 * anyOf是只要有一个任务完成，构造后CompletableFuture就完成
	 */
	public static void test1(){
		Long start = System.currentTimeMillis();
		// 结果集
		List<String> list = new ArrayList<>();

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
		// 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。
		// 返回结果whenComplete获取
		CompletableFuture[] cfs = taskList.stream()
				.map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
						.thenApply(h->Integer.toString(h))
						.whenComplete((s, e) -> {
							System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
							list.add(s);
						})
				).toArray(CompletableFuture[]::new);
		// 封装后无返回值，必须自己whenComplete()获取
		CompletableFuture.allOf(cfs).join();
		System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
	}

	/**
	 * 进行变换
	 * 入参是上一个阶段计算后的结果，返回值是经过转化后结果
	 */
	public static void thenApply() {
		String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
		System.out.println(result);
	}

	/**
	 * 进行消耗
	 *
	 * thenAccept是针对结果进行消耗，因为他的入参是Consumer，有入参无返回值。
	 */
	public void thenAccept(){
		CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s+" world"));
	}

	/**
	 * 对上一步的计算结果不关心，执行下一个操作
	 *
	 * thenRun它的入参是一个Runnable的实例，表示当得到上一步的结果时的操作
	 */
	public void thenRun(){
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenRun(() -> System.out.println("hello world"));
		while (true){}
	}

	/**
	 * 结合两个CompletionStage的结果，进行转化后返回
	 *
	 * 它需要原来的处理返回值，并且other代表的CompletionStage也要返回值之后，利用这两个返回值，进行转换后返回指定类型的值
	 */
	public void thenCombine() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		}), (s1, s2) -> s1 + " " + s2).join();
		System.out.println(result);
	}

	/**
	 * 结合两个CompletionStage的结果，进行消耗
	 *
	 * 它需要原来的处理返回值，并且other代表的CompletionStage也要返回值之后，利用这两个返回值，进行消耗。
	 */
	public void thenAcceptBoth() {
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		}), (s1, s2) -> System.out.println(s1 + " " + s2));
		while (true){}
	}

	/**
	 * 在两个CompletionStage都运行完执行
	 *
	 * 不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）
	 */
	public void runAfterBoth(){
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s1";
		}).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s2";
		}), () -> System.out.println("hello world"));
		while (true){}
	}

	/**
	 * 两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作
	 *
	 * 我们现实开发场景中，总会碰到有两种渠道完成同一个事情，所以就可以调用这个方法，找一个最快的结果进行处理
	 */
	public void applyToEither() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s1";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello world";
		}), s -> s).join();
		System.out.println(result);
	}

	/**
	 * 两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作
	 */
	public void acceptEither() {
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s1";
		}).acceptEither(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello world";
		}), System.out::println);
		while (true){}
	}

	/**
	 * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
	 */
	public void runAfterEither() {
		CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s1";
		}).runAfterEither(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "s2";
		}), () -> System.out.println("hello world"));
		while (true) {
		}
	}

	/**
	 * 当运行时出现了异常，可以通过exceptionally进行补偿
	 */
	public void exceptionally() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (1 == 1) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).exceptionally(e -> {
			System.out.println(e.getMessage());
			return "hello world";
		}).join();
		System.out.println(result);
	}

	/**
	 * 当运行完成时，对结果的记录。这里的完成时有两种情况，一种是正常执行，返回值。
	 * 另外一种是遇到异常抛出造成程序的中断。这里为什么要说成记录，因为这几个方法都会返回CompletableFuture，
	 * 当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。所以不会对结果产生任何的作用。
	 *
	 */
	public void whenComplete() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (1 == 1) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).whenComplete((s, t) -> {
			System.out.println(s);
			System.out.println(t.getMessage());
		}).exceptionally(e -> {
			System.out.println(e.getMessage());
			return "hello world";
		}).join();
		System.out.println(result);
	}

	/**
	 * 出现异常时
	 *
	 * 运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断
	 */
	public static void handle() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//出现异常
			if (1 == 1) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).handle((s, t) -> {
			if (t != null) {
				return "hello world";
			}
			return s;
		}).join();
		System.out.println(result);
	}


	public static void main(String[] args) throws InterruptedException {
		handle();
	}
}
