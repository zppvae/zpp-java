package org.java.vo;

import java.util.Date;

public class TestVO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		System.out.println(4 >> 1);
	}
}
