package org.java.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.java.vo.TestVO;

/**
 * 任何数据结构的构造或初始化，都应指定大小，避免数据结构无限增长吃光内存
 * Description:
 * @author zpp
 * @date   2017年10月12日
 */
public class AlibabaStand {
	
	public static void main(String[] args) {
		hashMap();
	}
	
	public static void equals(){
		String a = "1";
		TestVO vo = new TestVO();
		System.out.println(Objects.equals(a, "1"));
		System.out.println(Objects.equals(null, "1"));
		//Object 的 equals 方法容易抛空指针异常
		System.out.println(vo.getName().equals("1"));
	}
	
	/**
	 * 所有的相同类型的包装类对象之间值的比较，全部使用 equals 方法比较
	 */
	public static void integer(){}
	
	/**
	 * 在 subList 场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、
		删除均会产生 ConcurrentModificationException 异常
	 */
	public static void list(){
		List<Integer> list = new ArrayList<Integer>(7);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		List<Integer> subList = list.subList(1, 2);
		
		list.add(7);
		
		System.out.println(subList);
	}
	
	public static void toArray(){
		List<String> list = new ArrayList<String>(2);
		list.add("guan");
		list.add("bao");
		String[] array = new String[list.size()];
		array = list.toArray(array);
		System.out.println(array);
	}
	
	public static void asList(){
		String[] str = new String[] { "you", "wu" };
		List list = Arrays.asList(str);
		
//		list.add("11");//java.lang.UnsupportedOperationException
		
		str[0] = "888";
		System.out.println(list.get(0));
	}
	
	public static void foreach(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
//		for (Strings item : list) {
//			//2 : java.util.ConcurrentModificationException
//			if ("2".equals(item)) {
//				list.remove(item);
//			}
//		}
		//正解
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if ("1".equals(item)) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * 使用 entrySet 遍历 Map 类集合 KV ，而不是 keySet 方式进行遍历
	 * keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出key 所对应的 value
	 * 
	 * values() 返回的是 V 值集合，是一个 list 集合对象 ；keySet() 返回的是 K 值集合，是
                   一个 Set 集合对象 ；entrySet() 返回的是 K - V 值组合集合
	 */
	public static void hashMap(){
		HashMap<String,String> map = new HashMap<String,String>(16);
		map.put("a", "1");
		map.put("b", "2");
		Set<Entry<String, String>> set = map.entrySet();
		
		Iterator<Entry<String, String>> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().getValue());
		}
	}
	
	/**
	 * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式
	 * 
	 * 
	 *  CountDownLatch 进行异步转同步操作
	 */
	public static void thread(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
		
		//避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一seed 导致的性能下降
		ThreadLocalRandom.current().nextInt();
		//FIXME
		// volatile 解决多线程内存不可见问题
	}
	
	
}
