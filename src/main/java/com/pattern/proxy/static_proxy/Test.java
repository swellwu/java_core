package com.pattern.proxy.static_proxy;

import com.pattern.proxy.ISubject;

/**
 * Created by wuxinjian on 2017/2/21.
 */
public class Test {
    public static void main(String[] args) {
        ISubject proxySubject = new ProxySubject();
        proxySubject.request();
    }
}
