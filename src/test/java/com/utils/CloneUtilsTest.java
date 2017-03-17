package com.utils;

import com.utils.clone.CloneTestClass;
import com.utils.clone.CloneUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/17.
 */
public class CloneUtilsTest {

    private CloneTestClass object;



    @Before
    public void setUp(){
        object = new CloneTestClass();
    }

    @Test
    public void baseCloneTest() throws Exception{
        CloneTestClass object2 = object.clone();
        assertTrue(object.equals(object2));
        object2.changeList(1,1,1);
        assertTrue(object.equals(object2));
    }

    @Test
    public void serializableCloneTest() throws Exception {
        CloneTestClass object2 = CloneUtils.clone(object);
        assertTrue(object.equals(object2));
        object2.changeList(1,1,1);
        assertTrue(!object.equals(object2));
    }

    @Test
    public void cloneByJsonTest() throws Exception {
        CloneTestClass object2 = CloneUtils.cloneByJson(object);
        assertTrue(object.equals(object2));
        object2.changeList(1,1,1);
        assertTrue(!object.equals(object2));
    }

}