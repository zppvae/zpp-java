package org.java.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class TestList {
	
	public static void main(String[] args) {
//		test2();
		testListIterator();
	}
	
	/**
	 * subList返回的只是原列表的一个视图，它所有的操作最终都会作用在原列表上
	 */
	public static void test1(){
		List<Integer> list1 = new ArrayList<Integer>();  
        list1.add(1);  
        list1.add(2);  
          
        //通过构造函数新建一个包含list1的列表 list2  
        List<Integer> list2 = new ArrayList<Integer>(list1);  
          
        //通过subList生成一个与list1一样的列表 list3  
        List<Integer> list3 = list1.subList(0, list1.size());  
          
        //修改list3  
        list3.add(3);  
          
        System.out.println("list1 == list2：" + list1.equals(list2));  
        System.out.println("list1 == list3：" + list1.equals(list3)); 
	}
	
	/**
	 * subList生成子列表后，不要试图去操作原列表
	 */
	public static void test2(){
		List<Integer> list1 = new ArrayList<Integer>();  
        list1.add(1);  
        list1.add(2);  
          
        //通过subList生成一个与list1一样的列表 list3  
        List<Integer> list3 = list1.subList(0, list1.size());  
        //修改list3  
        list1.add(3);  
          
        System.out.println("list1'size：" + list1.size());  
        System.out.println("list3'size：" + list3.size());
	}
	
	/**
	 * 使用subList处理局部列表
	 */
	public static void test3(){
		List<Integer> list = new ArrayList<>();
		for(int i = 1;i <= 10;i++){
			list.add(i);
		}
		
		list.subList(2, 5).clear(); 
		
		System.out.println(list);
	}
	
	/**
	 * 在使用asList时不要将基本数据类型当做参数
	 *  asList接受的参数是一个泛型的变长参数，基本数据类型是无法泛型化的
	 *  
	 *  数组是一个对象，它是可以泛型化的。所以该例子是不会产生错误的。
	 *  既然例子是将整个int 类型的数组当做泛型参数，那么经过asList转换就只有一个int 的列表了
	 */
	public static void test4(){
		int[] ints = {1,2,3,4,5};  
        List list = Arrays.asList(ints);  
        System.out.println("list'size：" + list.size());  
	}
	
	/**
	 * 不要试图改变asList返回的列表
	 */
	public static void test5(){
		Integer[] ints = {1,2,3,4,5};  
        List list = Arrays.asList(ints);  
        //list.add(6);
        System.out.println("list'size：" + list.size());  
	}
	
	/**
	 * 便迭代边添加
	 */
	public static void testListIterator(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ListIterator it = list.listIterator();
        
        while (it.hasNext()) {
        	if (it.next().equals(1)) {
        		it.add(5);
        	}
        }
        
        System.out.println(list);
	}
}
