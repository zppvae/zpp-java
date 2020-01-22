package org.java.model.adapter;

/**
 * 
 * 源(Adapee)角色：我们要使用的接口，但是这个接口不符合我们的要求，也就是现在需要适配的接口
 * @author zpp
 *
 */
public class Adaptee {         
    public void specificRequest() {
         System.out.println("特殊请求，这个是源角色");
    }
}