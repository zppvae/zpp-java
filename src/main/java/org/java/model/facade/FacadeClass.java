package org.java.model.facade;

public class FacadeClass {
	public void FacadeMethod() {
		SubClass1 s1 = new SubClass1();
		s1.method1();
		SubClass2 s2 = new SubClass2();
		s2.method1();
		SubClass3 s3 = new SubClass3();
		s3.method1();
	}
}