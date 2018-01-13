package com.aop.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuxinjian on 2017/2/21.
 */
public class JdkDynamicProxy implements InvocationHandler {
    private Object obj;

    public JdkDynamicProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * 生成代理类工厂
     *
     * @param realObj
     * @return 返回生成的代理类
     * @author com.tiantian
     */
    public static Object getProxyInstance(Object realObj) {
        Class<?> classType = realObj.getClass();
        return Proxy.newProxyInstance(classType.getClassLoader(),
                classType.getInterfaces(), new JdkDynamicProxy(realObj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("before");
        method.invoke(obj, args);
        System.out.println("after");
        return null;
    }
}
