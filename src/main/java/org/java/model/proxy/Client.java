package org.java.model.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式 - 结构型模式
 *
 * 为其他对象提供一种代理以控制对这个对象的访问。
 * 在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用
 * Description:
 * @author zpp
 * @date   2018年3月28日
 */
public class Client {

	public static void main(String[] args) {
//		ProxySubject subject = new ProxySubject(new RealSubject());
//		subject.visit();
		
		Subject realSubject = new RealSubject();
		DynamicProxy proxy = new DynamicProxy(realSubject);
		ClassLoader classLoader = realSubject.getClass().getClassLoader();
		Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new  Class[]{Subject.class}, proxy);
		subject.visit();
	}
}