package org.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author zpp
 *
 */
public class HeapOOM {
	
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		
		while(true){
			list.add(new OOMObject());
		}
	}
}
