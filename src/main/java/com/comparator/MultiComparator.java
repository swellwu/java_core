package com.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wuxinjian on 2017/6/1.
 */

/**
 * 多重排序
 * 对多个学生排序规则如下：
 * 先按分数排，从大到小
 * 相同的按年龄排，从小到大
 * 相同的按身高排，从小到大
 */
public class MultiComparator {

    List<Student> studentList = new ArrayList<>();

    public MultiComparator addStudent(Student student) {
        studentList.add(student);
        return this;
    }

    public List<Student> getStudentList() {
        return studentList;
    }


    public void multiSort() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int dx = o1.getScore() - o2.getScore();
                int dy = o1.getAge() - o2.getAge();
                int dz = o1.getTall() - o2.getTall();
                if(dx!=0) return -dx;
                if(dy!=0) return dy;
                return dz;
            }
        });
    }

    public boolean checkSort() {
        Student first = studentList.get(0);
        int score = first.getScore();
        int age = first.getAge();
        int tall = first.getTall();
        for (Student student : studentList) {
            if (score > student.getScore() || (score == student.getScore() && age < student.getAge())
                    || (score == student.getScore() && age == student.getAge() && tall <= student.getTall())) {
                score = student.getScore();
                age = student.getAge();
                tall = student.getTall();
            } else {
                return false;
            }
        }
        return true;
    }

}
