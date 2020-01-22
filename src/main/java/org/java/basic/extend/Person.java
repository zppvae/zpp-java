package org.java.basic.extend;

public class Person {
	
	static{
		System.out.println("P2");
	}
	
	//构造代码块,构造代码块在创建对象时被调用,并且构造代码块的执行次序优先于类构造函数
	{
		System.out.println("P1");
	}
	
	public Person(){
		System.out.println("P3");
	}
}
