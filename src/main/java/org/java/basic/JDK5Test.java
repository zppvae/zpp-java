package org.java.basic;


public class JDK5Test {
	
	public static void main(String[] args) {
		share();
	}
	
	/**
	 * 享元模式思想(常常使用的东西，存放到公共空间，大家分享，节省内存)
	 * 
	 * 当一个整数大小在一个字节B(-128~ +127)的取值之间的时候，
	 * 当被自动包装为相应的对象之后，JVM就会将这些对应的基本数据类型值很小的基本数据类型对应的对象存入缓存池中，
	 * 以便程序的其他部分复用这个对象。因为Java认为值很小的对象使用频率很高
	 */
	public static void share(){
		Integer a = 10; 
		Integer b = 10;
		System.out.println("a == b ->"+(a == b));//true
		
		Integer c = new Integer(2);
		Integer d = new Integer(2);
		System.out.println("c == d ->"+(c == d));//内存地址不同 false
		
		Integer e = Integer.valueOf(5);
		Integer f = Integer.valueOf(5);
		System.out.println("e == f ->"+(e == f));//true
		
		Integer i3 =Integer.valueOf(137); 

		Integer i4 =Integer.valueOf(137);

		System.out.println(i3 ==i4);//打印false  >127
		
		String s1 = "123";
		String s2 = "123";
		String s3 = new String("123");//123是存储在常量池的字符串
		System.out.println("s1 == s2 ->"+(s1 == s2));//true
		System.out.println("s1 == s3 ->"+(s1 == s3));//false
		System.out.println("s1.equals(s3) ->"+(s1.equals(s3)));//true
	}
}
