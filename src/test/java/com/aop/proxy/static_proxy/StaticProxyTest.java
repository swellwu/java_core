package com.aop.proxy.static_proxy;

import com.aop.proxy.ISubject;
import com.aop.proxy.RealSubject;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.*;

/**
 * Created by wuxinjian on 2017/3/6.
 */
public class StaticProxyTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule();

    @Test
    public void staticProxyTest() throws Exception {
        log.enableLog();
        ISubject realSubject = new RealSubject();
        ISubject proxySubject = new ProxySubject(realSubject);
        proxySubject.request();
        assertEquals(
                "do something before\n" +
                        "realSubject requesting\n" +
                        "do something after\n",
                log.getLogWithNormalizedLineSeparator());

    }

}