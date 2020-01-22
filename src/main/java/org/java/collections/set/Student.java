package org.java.collections.set;

class Student implements Comparable {
	private String name;
	private int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return this.name + "::::" + this.age;
	}

	public int compareTo(Object o) {
		if (!(o instanceof Student))
			throw new RuntimeException("This is not a instance of Class \"Student\" ");

		Student s = (Student) o;
		sop(this.name + "......compareTo...." + s.name);
		if (this.age > s.age)
			return 1;
		else if (this.age == s.age)
			return 0;
		return -1;
	}

	public static void sop(Object o) {
		System.out.println(o);
	}
}
