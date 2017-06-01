package com.comparator;

/**
 * Created by wuxinjian on 2017/6/1.
 */

/**
 * 学生对象
 * 属性有年龄、姓名、分数、身高
 */
public class Student {
    int age;
    String name;
    int score;
    int tall;

    public Student(){

    }

    public Student(int score, int age, int tall) {
        this.age = age;
        this.score = score;
        this.tall = tall;
    }


    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append(score).append(",").append(age).append(",").append(tall).append('}');
        return sb.toString();
    }
}
