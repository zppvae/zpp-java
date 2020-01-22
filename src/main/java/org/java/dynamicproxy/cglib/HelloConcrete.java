package org.java.dynamicproxy.cglib;

public class HelloConcrete {

    public String sayHello(String str) {
        return "cglib proxy say hello : " + str;
    }
}