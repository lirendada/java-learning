package com.liren.model;

public class Student {
    // 私有属性name
    private String name = "bit";
    // 公有属性age
    public int age = 18;

    // 不带参数的构造⽅法
    public Student() {
        System.out.println("Student()");
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student(String,name)");
    }

    private void eat() {
        System.out.println("i am eat");
    }

    void sleep() {
        System.out.println("i am pig");
    }

    private void function(String str) {
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}