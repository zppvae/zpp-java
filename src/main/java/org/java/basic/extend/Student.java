package org.java.basic.extend;

public class Student extends Person{
	{
		System.out.println("S1");
	}
	static{
		System.out.println("S2");
	}
	
	
	public Student(){
		System.out.println("S3");
	}
	
	
	public static void main(String[] args) {
		new Student();
		new Student();
	}
}
