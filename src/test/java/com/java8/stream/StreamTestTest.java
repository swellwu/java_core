package com.java8.stream;

import org.junit.Test;

import javax.xml.bind.annotation.XmlMimeType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wuxinjian on 2017/4/22.
 */
public class StreamTestTest {

    @Test
    public void base1(){
        List<String> myList = Arrays.asList("a1","a2","b1","b2","c1","c2");
        myList.stream().filter(s->s.startsWith("c")).forEach(System.out::println);
    }
}