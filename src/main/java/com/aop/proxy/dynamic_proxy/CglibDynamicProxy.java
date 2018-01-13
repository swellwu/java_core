package com.aop.proxy.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-13
 */
public class CglibDynamicProxy implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = methodProxy.invokeSuper(target, args);
        System.out.println("after");
        return result;
    }
}
