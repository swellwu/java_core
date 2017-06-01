package com.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wuxinjian on 2017/4/22.
 */
public class StreamTest {

    public void base1() {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "b2", "c1");
        myList.stream().filter(s->s.startsWith("c")).forEach(System.out::println);
    }
}
