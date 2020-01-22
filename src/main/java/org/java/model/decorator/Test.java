package org.java.model.decorator;

/**
 * 装饰者模式
 * Description:
 * @author zpp
 * @date   2018年4月18日
 */
public class Test {

	public static void main(String[] args) {
		Man man = new Man();
		ManDecoratorA md1 = new ManDecoratorA();
		ManDecoratorB md2 = new ManDecoratorB();

		md1.setPerson(man);
		md2.setPerson(md1);
		md2.eat();
	}
}