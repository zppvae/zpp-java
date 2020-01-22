package org.java.dynamicproxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import org.java.dynamicproxy.cglib.HelloConcrete;
import org.java.dynamicproxy.cglib.TestMethodInterceptor;
import org.java.dynamicproxy.jdk.Hello;
import org.java.dynamicproxy.jdk.HelloImpl;
import org.java.dynamicproxy.jdk.TestInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @author zpp
 * @date 2019/9/28 15:14
 */
@Slf4j
public class Client {

    public static void main(String[] args){
        cglibProxy();
    }

    /**
     * jdk动态代理，基于接口
     */
    public static void jdkProxy(){
        Hello hello = (Hello)Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Hello.class},new TestInvocationHandler(new HelloImpl()));

        String str = hello.sayHello("zpp");
        log.info("[jdk] - [{}]",str);
    }

    /**
     * cglib动态代理，基于继承
     */
    public static void cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new TestMethodInterceptor());

        //得到代理对象
        HelloConcrete hello = (HelloConcrete)enhancer.create();

        String str = hello.sayHello("zpp");

        log.info("[cglib] - [{}]", str);
    }
}
