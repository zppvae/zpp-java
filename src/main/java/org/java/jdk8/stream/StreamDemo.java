package org.java.jdk8.stream;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.java.jdk8.Persion;

/**
 * stream
 *
 * 供应器（supplier）、累加器（accumulator）、组合器（combiner）和终止器（finisher）
 *
 * Stream API提供了两个静态方法来从函数生成流：Stream.iterate和Stream.generate。（无限流）
 *
 * 1、IntStream可以使用IntStream.range()替换通常的for循环
 *
 * Description:
 * @author zpp
 * @date   2018年5月16日
 */
@Slf4j
public class StreamDemo {
	
	public static void main(String[] args) {
//		testFilter();

// IntStream.range(0, 3).forEach(item ->{
//			System.out.println(item);
//		});

//		listToMap();
		Integer[] steps = {-4,-3,-2,-1,0,1,2,3,4};
		IntStream.range(0,steps.length).forEach(i ->{
			System.out.println(steps[i]);
		});
	}


	private static void testFilter(){
//		List<Strings> list = new ArrayList<>();
//		list.add("ddd2");
//		list.add("aaa3");
//		list.add("ddd1");
//		list.add("ddc2");
//		list.add("cdd2");
//		list.add("ddd1");
//		list.add("aaa2");
		
//		list.stream()
//			.sorted()
//			.filter((s) -> s.startsWith("d"))
//			.forEach(System.out::println);
		
//		list.stream()
//			.map(Strings::toUpperCase)
//			.sorted()
//			.forEach(System.out::println);
		
//		System.out.println("anyMatch:"+list.stream().anyMatch((s) -> s.startsWith("a")));
//		System.out.println("allMatch:"+list.stream().allMatch((s) -> s.startsWith("a")));
//		
//		//多个元素规约为一个元素
//		Optional<Strings> reduced = list.stream()
//										.sorted()
//										.reduce((a,b) -> a + "-" +b);
//		reduced.ifPresent(System.out::println);
		
		
		testStream();
	}
	
	/**
	 * 并行streams
	 */
	private static void testStream() {
		int max = 10000000;
		
		List<Integer> list = new ArrayList<>(max);
		for (int i = 0; i<max;i++) {
			list.add(RandomUtils.nextInt()+i);
		}
		
		long t0 = System.nanoTime();
		long count = list.stream().sorted().count();//串行排序
//		long count = list.parallelStream().sorted().count();//并行排序
		long t1 = System.nanoTime();
		long mills = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		
		System.out.println(String.format("串行排序：%d ms", mills));
//		System.out.println(Strings.format("并行排序：%d ms", mills));
		
		
	}

	public static int doubleNum(int i) {
		System.out.println("执行了乘以2");
		return i * 2;
	}

	public static void testDoubleNum(){
		int[] nums = { 1, 2, 3 };
		// 外部迭代
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println("结果为：" + sum);

		// 使用stream的内部迭代
		// map就是中间操作（返回stream的操作）
		// sum就是终止操作
		int sum2 = IntStream.of(nums).map(StreamDemo::doubleNum).sum();
		System.out.println("结果为：" + sum2);

		System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行，以下不会输出");
		IntStream.of(nums).map(StreamDemo::doubleNum);
	}

	/**
	 * 终止操作
	 */
	public static void  testStopOperate(){
		String str = "my name is 007";

		// 使用并行流
		str.chars().parallel().forEach(i -> System.out.print((char) i));
		System.out.println();
		// 使用 forEachOrdered 保证顺序
		str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

		// 收集到list
		List<String> list = Stream.of(str.split(" "))
				.collect(Collectors.toList());
		System.out.println(list);

		// 使用 reduce 拼接字符串
		Optional<String> letters = Stream.of(str.split(" "))
				.reduce((s1, s2) -> s1 + "|" + s2);
		System.out.println(letters.orElse(""));

		// 带初始化值的reduce
		String reduce = Stream.of(str.split(" ")).reduce("",
				(s1, s2) -> s1 + "|" + s2);
		System.out.println(reduce);

		// 计算所有单词总长度
		Integer length = Stream.of(str.split(" ")).map(s -> s.length())
				.reduce(0, (s1, s2) -> s1 + s2);
		System.out.println(length);

		// max 的使用
		Optional<String> max = Stream.of(str.split(" "))
				.max((s1, s2) -> s1.length() - s2.length());
		System.out.println(max.get());

		// 使用 findFirst 短路操作
		OptionalInt findFirst = new Random().ints().findFirst();
		System.out.println(findFirst.getAsInt());
	}

	/**
	 * 中间操作
	 */
	public static void middleOperate(){
		String str = "my name is 007";

		// 把每个单词的长度调用出来
		Stream.of(str.split(" ")).filter(s -> s.length() > 2)
				.map(s -> s.length()).forEach(System.out::println);

		// flatMap A->B属性(是个集合), 最终得到所有的A元素里面的所有B属性集合
		// intStream/longStream 并不是Stream的子类, 所以要进行装箱 boxed
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
				.forEach(i -> System.out.println((char) i.intValue()));

		// peek 用于debug. 是个中间操作,和 forEach 是终止操作
		System.out.println("--------------peek------------");
		Stream.of(str.split(" ")).peek(System.out::println)
				.forEach(System.out::println);

		// limit 使用, 主要用于无限流
		new Random().ints().filter(i -> i > 100 && i < 1000).limit(10)
				.forEach(System.out::println);
	}

	public static void supplier() {
		//创建一个数据流供应器，来构建新的数据流
		Supplier<Stream<String>> streamSupplier =
				() -> Stream.of("d2", "a2", "b1", "b3", "c")
						.filter(s -> s.startsWith("a"));

		//每次对get()的调用都构造了一个新的数据流
		streamSupplier.get().anyMatch(s -> true);   // ok
		streamSupplier.get().noneMatch(s -> true);  // ok
	}

	/**
	 * 将List转成map
	 */
	public static void listToMap(){
		List<Persion> list = new ArrayList<>();
		list.add(new Persion(1,"a1"));
		list.add(new Persion(2,"a2"));
		list.add(new Persion(3,"a3"));

		Map<Integer, Persion> persionMap = list.stream()
				.collect(Collectors.toMap(u -> u.getId(), u -> u));

		log.info("[将List转成map] - [{}]",persionMap);
	}

}
