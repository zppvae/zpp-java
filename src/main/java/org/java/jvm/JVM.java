package org.java.jvm;

import java.io.IOException;


/**
 * -Xmx20m -Xms20m -Xmn10m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=75
 * 
 * 调整后：-Xmx20m -Xms20m -Xmn10m -XX:+UseParNewGC
 * 
 * 
 * -XX:+UseParNewGC 表示新生代使用ParNew并行收集器
 * -XX:+UseConcMarkSweepGC 表示老年代使用CMS回收器
 * -XX:CMSInitiatingOccupancyFraction=75 表示当老年代的使用率达到阈值75%时会触发CMS GC
 * 
 * Description:
 * @author zpp
 * @date   2018年5月15日
 */
public class JVM {
	
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) throws IOException {

		byte[] all1 = new byte[2 * _1MB];
		byte[] all2 = new byte[2 * _1MB];
		byte[] all3 = new byte[2 * _1MB];
		byte[] all4 = new byte[7 * _1MB];
		
		System.in.read();
	}
	
}
