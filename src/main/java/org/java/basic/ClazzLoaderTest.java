package org.java.basic;

/**
 * https://blog.csdn.net/benjaminzhang666/article/details/9935075
 * @author zpp
 *
 */
public class ClazzLoaderTest {

	public static void main(String[] args) {
		ClassLoader classLoader = ClazzLoaderTest.class.getClassLoader();

		while (classLoader != null) {
			System.out.println(classLoader.getClass().getName());
			classLoader = classLoader.getParent();
		}
		System.out.println(classLoader);//Bootstrap不是Java类，所以不可能有Java对象
	}
}
