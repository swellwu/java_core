package com.covariant;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Sub extends Base {

    @Override
    String method1() {
        System.out.println("Sub : method1");
        return "method1";
    }

    void method2(Object arg) {
        System.out.println("Sub : method2");
    }

    String method3(Object arg) {
        System.out.println("Sub : method3");
        return "method3";
    }
}
