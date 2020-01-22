package org.java.jdk8;

public class PersionClient {
	
	public static void main(String[] args) {
		PersionFactory<Persion> factory = Persion::new;
		//编译器自动选择合适的构造函数
		Persion p = factory.create("zpp", "xxx");
		System.out.println(p.getFirstName());
	}
}
