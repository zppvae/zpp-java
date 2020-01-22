package org.java.model.adapter;

/**
 * 对象的适配器模式不是使用继承关系连接到Adaptee类，而是使用委派关系连接到Adaptee类
 * @author zpp
 *
 */
public class Test {
	public static void main(String[] args) {
		// 使用特殊功能类，即适配类
		Target adapter = new ClazzAdapter();
		adapter.request();
	}
}