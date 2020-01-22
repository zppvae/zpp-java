package org.java.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * https://mp.weixin.qq.com/s?__biz=MzIzNjI1ODc2OA==&mid=2650887067&idx=1&sn=2ef05e1457b7d6db4886be7b0bf9a8b3&scene=19#wechat_redirect
 * 使用一下参数运行  JDK 8
 * -Xmx300M -Xms300M  -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintReferenceGC
 * JNI Weak Reference 耗时增长
 * Description:
 * @author zpp
 * @date   2017年8月21日
 */
public class MHTest {
	
	static MethodHandles.Lookup lookup = MethodHandles.lookup();
	
	public static void main(String[] args) {
		while(true){
			MethodType type = MethodType.methodType(double.class,double.class);
			try {
				MethodHandle mh = lookup.findStatic(Math.class, "log", type);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
