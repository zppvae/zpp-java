package org.java.jdk8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

		test1();
	}

	public static void test1(){
		String s1 = "11";
		Persion p = new Persion();
		p.setId(1);
		p.setFirstName("zpp");
		p.setLastName("zzpp");
		List<String> list = new ArrayList<>();
		list.add("aaa");
		s1 = test2(s1, list,p);
		System.out.println(s1);
		System.out.println(list);
		System.out.println(p);
	}

	public static String test2(String s1, List<String> list, Persion p){
		s1 = "22333";
		list.add("bbb");
		p.setFirstName("oooaaaass");
		return s1;
	}
}
