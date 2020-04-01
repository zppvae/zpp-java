package org.java.collections.map;

import java.util.HashMap;
import java.util.UUID;

/**
 * 死循环的HashMap 
 * 在多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%
 * 
 * 
 * HashMap在并发执行put操作时会引起死循环，是因为多线程会导致HashMap的Entry链表
	形成环形数据结构，一旦形成环形数据结构，Entry的next节点永远不为空，就会产生死循环获
	取Entry。
 * @author zpp
 * @date 2018年5月21日
 */
public class DeadLoopHashMap {

	private static HashMap<Integer, String> map = new HashMap<Integer, String>(2,1.5f);

	public static void main(String[] args) throws InterruptedException {
		map.put(5,"C");
		map.put(7,"B");
		map.put(3,"A");

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				map.put(15,"D");
				System.out.println(map);
			}
		}, "t1");
		t.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				map.put(1,"E");
				System.out.println(map);
			}
		}, "t2");
		t2.start();

	}
}
