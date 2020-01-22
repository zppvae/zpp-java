package org.java.jvm;

import java.util.ArrayList;
import java.util.List;

public class JConsoleTest {
//	byte[] b = new byte[128 * 1024];
	
	public JConsoleTest(){
		byte[] b = new byte[128 * 1024];
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(5000);
		fill(1000);
	}
	
	private static void fill(int count){
		List<JConsoleTest> list = new ArrayList<>();
		for(int i = 0; i < count;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.add(new JConsoleTest());
		}
	}
}
