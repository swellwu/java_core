package com.jdk;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by wuxinjian on 2017/6/2.
 */
public class StringTest {

    @Test
    public void stringEqualsTest() {
        String s1 = "hello world";
        String s2 = new String("hello world");
        String s3 = new String(s1);
        String s4 = "hello" + " world";
        assertTrue(s1 != s2);
        assertTrue(s1 != s3);
        //s4会在编译期优化成"hello world",和是公用字符常量中的引用，所以引用相同
        assertTrue(s1 == s4);
        s1.intern();
    }

    @Test
    public void internTest() {
        String s1 = new String("abc");
        String s2 = "abc";
        assertTrue(s1 != s2);
        //intern返回字符串常量值的引用，当且仅当s1、s2 equals=true时，他们的intern才相等
        assertTrue(s1.intern() == s2.intern());
    }
}
