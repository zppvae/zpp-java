package org.java.model.factory.simple;

/**
 * 具体产品角色
 * Description:
 * @author zpp
 * @date   2018年4月24日
 */
public class ProductB implements IProduct {
	@Override
	public void method() {
		System.out.println("I'm ProductB!");
	}
}