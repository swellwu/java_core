package com.pattern.proxy.static_proxy;

import com.pattern.proxy.ISubject;
import com.pattern.proxy.RealSubject;

/**
 * Created by wuxinjian on 2017/2/21.
 */
public class ProxySubject implements ISubject {
    private ISubject realSubject;
    public ProxySubject(ISubject subject) {
        realSubject=subject;
    }

    @Override
    public void request() {
        System.out.println("do something before");
        realSubject.request();
        System.out.println("do something after");
    }
}
