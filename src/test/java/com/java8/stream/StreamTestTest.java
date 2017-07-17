package com.java8.stream;

import com.comparator.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springside.modules.utils.base.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by wuxinjian on 2017/4/22.
 */
public class StreamTestTest {

    List<Student> list = new ArrayList<>();

    @Test
    public void toList() {
        IntStream.range(1, 10).forEach(i -> list.add(new Student(i, 2 * i, 3 * i)));
        List<Integer> scoreList = list.stream().map(s -> s.getScore()).collect(Collectors.toList());
        Assert.assertArrayEquals(scoreList.toArray(),new Integer[]{1,2,3,4,5,6,7,8,9});
        System.out.println(ObjectUtil.toPrettyString(scoreList));
    }
}