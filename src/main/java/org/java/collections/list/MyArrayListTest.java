package org.java.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyArrayListTest {
	
	public static void main(String[] args) {
//		MyArrayList<Strings> arr = new MyArrayList<>();
//		arr.add("1");
//		arr.add("2");
//		arr.add("3");
//		arr.add("4");
//		arr.add("5");
//		System.out.println(arr.size());
////		System.out.println("删除下标是1的元素："+arr.remove(1)+",删除后元素的大小："+arr.size());
//		System.out.println("删除指定元素 "+arr.remove(3));
//		System.out.println("获取指定下标元素："+arr.get(3));
		
		ArrayList<String> arr = new ArrayList<>();
		arr.add("1");
		arr.add("2");
		arr.add("3");
		arr.add("4");
		arr.add("5");
		System.out.println("删除指定元素 "+arr.remove(3));
		System.out.println("获取指定下标元素："+arr.get(3));
		
		
		
	}
}
