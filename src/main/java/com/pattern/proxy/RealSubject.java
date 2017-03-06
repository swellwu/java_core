package com.pattern.proxy;

/**
 * Created by wuxinjian on 2017/2/21.
 */
public class RealSubject implements ISubject{

    @Override
    public void request() {
        System.out.println("realSubject requesting");
    }
}