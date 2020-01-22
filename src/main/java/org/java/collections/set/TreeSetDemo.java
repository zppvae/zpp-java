package org.java.collections.set;

import java.util.Iterator;
import java.util.TreeSet;

class TreeSetDemo {
	public static void sop(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		TreeSet ts = new TreeSet();
		ts.add(new Student("lisi02", 22));
		ts.add(new Student("lisi007", 20));
		ts.add(new Student("lisi09", 19));

		Iterator it = ts.iterator();
		while (it.hasNext()) {
			sop(it.next());
		}
	}
}