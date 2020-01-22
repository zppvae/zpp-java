package org.java.basic;

public class GenericDemo {
	
	/**
	 * 非静态泛型方法的定义
	 * 将< >将类型参数定义在方法上。具体是访问修饰符和返回值类型之间
	 * @param t
	 */
	public <T> void show(T t) {
		System.out.println("show:" + t);
	}

	public <T> void print(T t) {
		System.out.println("print:" + t);
	} 
	
	public static <W> void printStatic(W w){  
        System.out.println("static method: "+w);  
    } 
}
