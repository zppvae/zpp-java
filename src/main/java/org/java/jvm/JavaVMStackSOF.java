package org.java.jvm;

/**
 * -Xss128k
 * @author zpp
 *
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength ++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF sof = new JavaVMStackSOF();
	}
}
