package org.java.jdk8;

public interface PersionFactory<P extends Persion> {

	P create(String firstName,String lastName);
}
