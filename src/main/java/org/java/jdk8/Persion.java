package org.java.jdk8;

import lombok.Data;

@Data
public class Persion {

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	public Persion(){}

	public Persion(Integer id,String firstName){
		this.id = id;
		this.firstName = firstName;
	}

	public Persion(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
}
