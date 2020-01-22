package org.java.concurrency.thread.future;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 分支/合并框架计算求和
 *
 * https://github.com/jojozhai/zkh-commodity/tree/master/commodity/doc
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
	/**
	 *
	 */
	private static final long serialVersionUID = -4008111329500774405L;

	private final long[] numbers;
	private final int start;
	private final int end;

	//不再将任务分解为子任务的数组大小
	private static final long LIMIT = 10000;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	/**
	 * 用于以递归方式为主任务创建子任务
	 * @param numbers
	 * @param start
	 * @param end
	 */
	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		//如果大小小于或等于阈值，则不再拆分任务，顺计算结果
		if(length <= LIMIT) {
			return computeSum();
		}

		//创建一个子任务来为数组的前一半求和
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		//利用另一个线程异步执行新创建的子任务
		leftTask.fork();

		//创建一个子任务来为数组的后一半求和
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

		//同步执行第二个子任务，有可能允许进一步递归划分
		Long rightResult = rightTask.compute();
		//读取第一个子任务的结果，如果尚未完成就等待
		Long leftResult = leftTask.join();

		return rightResult + leftResult;
	}

	private Long computeSum() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += numbers[i];

		}
		return sum;
	}

	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(0, 100000000).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		long now = System.currentTimeMillis();
		System.out.println(new ForkJoinPool().invoke(task));
		System.out.println("耗时:" + (System.currentTimeMillis() - now));
	}

}