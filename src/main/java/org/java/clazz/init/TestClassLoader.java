package org.java.clazz.init;

import java.util.ServiceLoader;

/**
 * 用户自定义的classloader – AppClassLoader —ExtClassLoader – bootStrapClassLoader
 * Description:
 * @author zpp
 * @date   2018年3月26日
 */
public class TestClassLoader {
	public static void main(String[] args) {
		ClassLoader myClassLoader = new ClassLoader() {};
		try {
			Class cls = myClassLoader.loadClass(TestClassLoader.class.getName());
			System.out.println(myClassLoader.getParent());
			System.out.println(cls.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		test2();
	}
	
	
	public static void test2() {
		ServiceLoader<SPITest> loaders = ServiceLoader.load(SPITest.class);
		loaders.forEach(i -> {
			i.test();
		});
	}
}