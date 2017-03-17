package com.utils.clone;

import com.google.gson.Gson;

import java.io.*;

/**
 * Created by Administrator on 2017/3/17.
 */

/**
 * 利用序列化实现对象的深层拷贝
 * 类似的，可以利用json字符串实现对象拷贝
 */
public class CloneUtils {


    /**
     * 对象必须实现序列化接口
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public static <T> T cloneByJson(T obj){
        Gson gson =new Gson();
        String json=gson.toJson(obj);
        return (T)gson.fromJson(json,obj.getClass());
    }
}
