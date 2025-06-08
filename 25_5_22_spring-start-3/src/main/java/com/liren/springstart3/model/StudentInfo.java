package com.liren.springstart3.model;

import lombok.Data;

@Data
public class StudentInfo {
    private String name;
    private String passwd;
    private int age;

    public StudentInfo(String name, int age, String passwd) {
        this.name = name;
        this.passwd = passwd;
        this.age = age;
    }

    public StudentInfo() {}
}
