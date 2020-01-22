package org.java.model.proxy;

public class ProxySubject implements Subject {

	private Subject subject;

	public ProxySubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void visit() {
		subject.visit();
		System.out.println("proxy ...");
	}
}