package org.java.collections.set;

import java.util.Comparator;

class MyComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		if (!((o1 instanceof Student) && (o2 instanceof Student))) {
			throw new RuntimeException("Sorry");
		}
		Student s1 = (Student) o1;
		Student s2 = (Student) o2;

		int num = s1.getName().compareTo(s2.getName());
		if (num == 0)
			return s1.getAge() - s2.getAge();
		return num;
	}
}