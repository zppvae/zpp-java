package org.java.model.decorator;

/**
 * 抽象装饰者：持有对具体构件角色的引用并定义与抽象构件角色一致的接口
 * Description:
 * @author zpp
 * @date   2018年3月28日
 */
public abstract class Decorator implements Person {

	protected Person person;

	public void setPerson(Person person) {
		this.person = person;
	}

	public void eat() {
		person.eat();
	}
}