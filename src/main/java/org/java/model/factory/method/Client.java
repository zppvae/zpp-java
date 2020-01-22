package org.java.model.factory.method;

/**
 * 提前定义用于创建对象的接口，让子类决定实例化具体的某一个类，即在工厂和产品中间增加接口，
 * 工厂不再负责产品的创建，由接口针对不同条件返回具体的类实例，由具体类实例去实现。
 * 
 * 降低了耦合度,扩展性高
 * Description:
 * @author zpp
 * @date   2018年3月27日
 */
public class Client {
	
	public static void main(String[] args) {
		ICarFactory factory = null;
		// bike
		factory = new BikeFactory();
		Car bike = factory.getCar();
		bike.gotowork();

		// bus
		factory = new BusFactory();
		Car bus = factory.getCar();
		bus.gotowork();
	}
}