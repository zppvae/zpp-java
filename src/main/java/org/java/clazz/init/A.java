package org.java.clazz.init;

public class A {
	
	static{
		System.out.println(0);
		B.test();
	}
	public static void test(){}
}

