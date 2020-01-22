package org.java.jdk8;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	
	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<>();
		
		for (int i = 0;i < 10;i++) {
			map.putIfAbsent(i, "val-"+i);
		}
		
		map.forEach((id,val) -> System.out.println(val));
		
		map.merge(5, "aa", (value,newValue) -> value.concat(newValue));
		System.out.println(map.get(5));
	}
}
