package com.pattern.proxy.dynamic_proxy;

import com.pattern.proxy.ISubject;
import com.pattern.proxy.RealSubject;

/**
 * Created by wuxinjian on 2017/2/21.
 */
public class Test {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ISubject subject = (ISubject)SubjectInvocationHandler.getProxyInstanceFactory(realSubject);
        subject.request();
    }
}
