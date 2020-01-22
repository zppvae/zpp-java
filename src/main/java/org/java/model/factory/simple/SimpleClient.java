package org.java.model.factory.simple;

/**
 * 简单工厂
 * 
 * Java中java.text.DateFormat就是简单工厂模式的典型案例
 * Description:
 * @author zpp
 * @date   2018年4月24日
 */
public class SimpleClient {
	
	public static void main(String[] args) throws Exception {
		SimpleFactory factory = new SimpleFactory();
//		IProduct product = factory.produce("A");		
        IProduct product = factory.produce(ProductB.class);
        product.method();
	}
}
