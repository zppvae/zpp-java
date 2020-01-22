package org.java.model.factory.method;

/**
 * 具体产品
 * Description:
 * @author zpp
 * @date   2018年4月24日
 */
public class Bike implements Car {
	@Override
	public void gotowork() {
		System.out.println("骑自行车去上班！");
	}
}