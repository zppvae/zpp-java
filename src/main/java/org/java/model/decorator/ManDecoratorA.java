package org.java.model.decorator;

/**
 * 具体装饰：实现抽象装饰者角色，负责对具体构件添加额外功能
 * Description:
 * @author zpp
 * @date   2018年3月28日
 */
public class ManDecoratorA extends Decorator {

	public void eat() {
		super.eat();
		reEat();
		System.out.println("ManDecoratorA类");
	}

	public void reEat() {
		System.out.println("再吃一顿饭");
	}
}