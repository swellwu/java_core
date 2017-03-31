package com.jvm;

import org.omg.SendingContext.RunTime;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/20.
 */
public class PutInEden {
    public static void main(String[] args) {
        byte[] b1,b2,b3,b4;//定义变量
        b1=new byte[1024*1024];//分配 1MB 堆空间，考察堆空间的使用情况
        b2=new byte[1024*1024];
        b3=new byte[1024*1024];
        b4=new byte[1024*1024];
        Map<String,String> linkedMap = new WeakHashMap<>();
        linkedMap.put("d","d");
        linkedMap.put("c","c");
        linkedMap.put("a","aaaa");
        linkedMap.put("b","b");
        linkedMap.put("c","ccc");
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Map.Entry one : linkedMap.entrySet()){
            System.out.println(one.getKey());
            System.out.println(one.getValue());
        }
    }
}
