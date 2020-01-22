package org.java.dynamicproxy.jdk;


public class HelloImpl implements Hello {
    @Override
    public String sayHello(String str) {
        return "jdk proxy say hello: " + str;
    }
}