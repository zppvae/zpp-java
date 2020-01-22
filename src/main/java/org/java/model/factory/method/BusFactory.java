package org.java.model.factory.method;

public class BusFactory implements ICarFactory {
	@Override
	public Car getCar() {
		return new Bus();
	}
}