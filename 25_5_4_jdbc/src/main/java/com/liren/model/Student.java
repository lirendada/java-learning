package com.liren.model;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private String sno;
    private Integer age;
    private byte gender;
    private Date enroll_date;
    private Long class_id;

    private Classes cs; // 与课程表关联

    public Classes getCs() {
        return cs;
    }

    public void setCs(Classes cs) {
        this.cs = cs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sno='" + sno + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", enroll_date=" + enroll_date +
                ", class_id=" + class_id +
                '}';
    }
}
