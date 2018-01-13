package com.pattern.proxy.dynamic_proxy;

import com.pattern.proxy.ISubject;
import com.pattern.proxy.RealSubject;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wuxinjian on 2017/3/6.
 */
public class DynamicProxyTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule();

    @BeforeClass
    public static void setUp(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    @Test
    public void SdkDynamicProxyTest() throws Exception {
        log.enableLog();
        RealSubject realSubject = new RealSubject();
        ISubject subject = (ISubject) JdkDynamicProxy.getProxyInstance(realSubject);
        subject.request();
        assertEquals("before\n"+
                "realSubject requesting\n"+
                "after\n",
                log.getLogWithNormalizedLineSeparator());
        log.clearLog();
        System.out.println(subject.getClass());
        assertTrue(log.getLog().contains("$Proxy"));
    }

    @Test
    public void CglibDynamicProxyTest(){
        log.enableLog();
        RealSubject realSubject = new RealSubject();
        ISubject subject = (ISubject) new CglibDynamicProxy().getProxyInstance(realSubject);
        subject.request();
        assertEquals("before\n"+
                        "realSubject requesting\n"+
                        "after\n",
                log.getLogWithNormalizedLineSeparator());
        log.clearLog();
        System.out.println(subject.getClass().getSuperclass());
    }
}