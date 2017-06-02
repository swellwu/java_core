package com.comparator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by wuxinjian on 2017/6/1.
 */
public class MultiComparatorTest {

    MultiComparator multiComparator = new MultiComparator();

    @Before
    public void init() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; ++i) {
            Student student = new Student();
            int scoreMin = 0;
            int scoreMax = 100;
            int score = scoreMin + Math.abs(random.nextInt()) % Math.subtractExact(scoreMax, scoreMin);
            student.setScore(score);
            int ageMin = 10;
            int ageMax = 20;
            int age = ageMin + Math.abs(random.nextInt()) % Math.subtractExact(ageMax, ageMin);
            student.setAge(age);
            int tallMin = 120;
            int tallMax = 210;
            int tall = tallMin + Math.abs(random.nextInt()) % Math.subtractExact(tallMax, tallMin);
            student.setTall(tall);
            multiComparator.addStudent(student);
        }
        System.out.println("init 500 students finished。");
    }

    @Test
    public void multiSort() throws Exception {
        System.out.println("sort before:");
        printStudentList(multiComparator.getStudentList());
        multiComparator.multiSort();
        boolean sortedTrue = multiComparator.checkSort();
        System.out.println("sort after:");
        printStudentList(multiComparator.getStudentList());
        assertTrue(sortedTrue);
    }

    void printStudentList(List<Student> list) {
        System.out.println(list);
    }
}