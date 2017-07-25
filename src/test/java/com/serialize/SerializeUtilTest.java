package com.serialize;

import com.vo.Monster;
import org.junit.Assert;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author swellwu
 * @create 2017-07-25-22:50
 */
public class SerializeUtilTest {

    Monster monster = new Monster("boss", 100);
    Class clazz = Monster.class;

    @Test
    public void jdkSerializeTest() throws Exception {
        byte[] bytes = SerializeUtil.JDKObjectToBytes(monster);
        System.out.println(bytes.length);
        Monster object = SerializeUtil.JDKBytesToObject(bytes,clazz);
        Assert.assertEquals(object, monster);
    }

    @Test(expected = com.alibaba.fastjson.JSONException.class)
    public void fastJsonSerializeTest(){
        byte[] bytes = SerializeUtil.FastJsonObjectToBytes(monster);
        System.out.println(bytes.length);
        Monster object = SerializeUtil.FastJsonBytesToObject(bytes,clazz);
        Assert.assertEquals(object, monster);
    }

    @Test
    public void serialize(){
        byte[] serialize = SerializeUtil.serialize(monster);
        System.out.println(serialize.length);
        Object deserialize = SerializeUtil.deserialize(serialize, clazz);
        Assert.assertEquals(deserialize,monster);
    }
}