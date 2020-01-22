package org.java.model.factory.method;

/**
 * 具体工厂
 * Description:
 * @author zpp
 * @date   2018年4月24日
 */
public class BikeFactory implements ICarFactory {
	@Override
	public Car getCar() {
		return new Bike();
	}
}