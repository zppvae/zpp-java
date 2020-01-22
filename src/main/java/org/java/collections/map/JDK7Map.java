package org.java.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JDK7Map {
	
	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<>(5);
		
		for (int i = 0;i < 10;i++) {
			map.put(i+"", i + 1);
		}
		
	}
}
