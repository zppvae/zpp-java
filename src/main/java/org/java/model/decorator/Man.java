package org.java.model.decorator;

/**
 * 具体组件：将要被附加功能的类，实现抽象构件角色接口
 * Description:
 * @author zpp
 * @date   2018年3月28日
 */
public class Man implements Person {

	public void eat() {
		System.out.println("男人在吃");
	}
}