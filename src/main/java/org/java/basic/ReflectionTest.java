package org.java.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 反射
 * @author zpp
 *
 */
public class ReflectionTest {
	
	public static void main(String[] args) throws Exception {
		Class clazz = Class.forName("org.java.collections.set.Person");
//		System.out.println(clazz.getDeclaredFields()[0]);
		
//		Method[] methods =clazz.getMethods();  
//		for(Method method: methods){  
//		    sop(method);  
//		}  
		
		Constructor constructor =clazz.getConstructor(String.class, int.class);  
//		Object obj = constructor.newInstance("Benjamin", 28);  
//		Method method =clazz.getMethod("show", null);  //调用无参方法
//		method.invoke(obj, null);  
		
		//有参
//		Object obj = clazz.newInstance();  
//		Method method =clazz.getMethod("paramMethod", Strings.class, int.class);
//		method.invoke(obj, "Benjamin", 110); 
		
		Object obj = constructor.newInstance("Benjamin", 28);  
		Method method =clazz.getDeclaredMethod("showII", null);  
		//指示JVM取消对访问权限的检查  
		method.setAccessible(true);  
		method.invoke(obj, null);  
		
	}
	
	public static void sop(Object o){  
        System.out.println(o);  
    } 
}
