package org.java.jvm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk6、7会有问题，jdk8没问题
 * 
 * Map其实是一个ConcurrentHashMap，是线程安全的，但是这个map里的value是一个HashSet，这个HashSet是非线程安全的，并且存在多个线程修改这个Set的情况
 * Description:
 * @author zpp
 * @date   2017年8月21日
 */
public class OOM {
	
	private static Map<Long,Set<Integer>> setMap = new ConcurrentHashMap<Long,Set<Integer>>();
	
	public static void main(String[] args) {
		final Long key = 1L;
		
		setMap.put(key, new HashSet<Integer>());
		
		for(int i=0;i<100;i++){
			setMap.get(key).add(i);
		}
		
		Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int j=100;j<200000;j++){
					setMap.get(key).add(j);
				}
			}
		});
		
		Thread b = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int j=200000;j<400000;j++){
					setMap.get(key).add(j);
				}
			}
		});
		
		a.start();
		b.start();
		
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(setMap.toString());
	}
	
}









