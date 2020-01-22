package org.java.dynamicproxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zpp
 * @date 2019/9/28 15:27
 */
@Slf4j
public class TestMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("[cglib proxy，在方法执行前增加逻辑处理] - [{}]",method.getName());

        //转发到原始对象调用
        return methodProxy.invokeSuper(o, args);
    }
}
