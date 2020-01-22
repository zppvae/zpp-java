package org.java.model.factory.simple;

/**
 * 工厂类角色
 * Description:
 * @author zpp
 * @date   2018年4月24日
 */
public class SimpleFactory {
	public IProduct produce(String type) {
		if ("A".equals(type)) {
			return new ProductA();
		} else if ("B".equals(type)) {
			return new ProductB();
		} else {
			System.out.println("请输入正确的类型");
			return null;
		}
	}
	
	/**
	 * 采用反射机制实现简单工厂    
	 * 通常，普通应用程序在运行时不应该以反射方式访问对象
	 * 【接口优先于反射机制】
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public IProduct produce(Class<? extends IProduct> c) throws Exception {
		return (IProduct) Class.forName(c.getName()).newInstance();
		// return (IProduct)c.newInstance(); //或者采用这种方法
	}
}