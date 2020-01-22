package org.java.model.proxy;

public class RealSubject implements Subject {

	private String name = "real object";

	@Override
	public void visit() {
		System.out.println(name);
	}
}