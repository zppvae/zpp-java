package org.java.collections.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * 当元素和容器本身都具有比较性的时候，容器比较器优先
 * 
 * https://blog.csdn.net/benjaminzhang666/article/details/9474119
 * 
 * @author zpp
 *
 */
class TreeSetDemoII {
	public static void sop(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		TreeSet ts = new TreeSet(new MyComparator());
		ts.add(new Student("lisi02", 22));
		ts.add(new Student("lisi007", 20));
		ts.add(new Student("lisi09", 19));
		ts.add(new Student("lisi09", 18));

		Iterator it = ts.iterator();
		while (it.hasNext()) {
			sop(it.next());
		}
	}
}