package org.java.dynamicproxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zpp
 * @date 2019/9/28 15:11
 */
@Slf4j
public class TestInvocationHandler implements InvocationHandler {

    private Hello hello;

    public TestInvocationHandler (Hello hello) {
        this.hello = hello;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayHello".equals(method.getName())) {
            log.info("[jdk proxy，在方法执行前增加逻辑处理] - [{}]",method.getName());
        }
        return method.invoke(hello,args);
    }
}
