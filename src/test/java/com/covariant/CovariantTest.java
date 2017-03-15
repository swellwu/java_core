package com.covariant;

import org.junit.Test;
import org.omg.CORBA.Object;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/13.
 */
public class CovariantTest {

    @Test
    public void baseCovariantTest(){
        Base base=new Base();
        base.method1();
        base.method2("");
        base.method3("");

        base = new Sub();
        base.method1();
        base.method2("");
        base.method3("");
    }
}