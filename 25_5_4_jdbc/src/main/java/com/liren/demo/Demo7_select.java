package com.liren.demo;

import com.liren.dao.StudentDAO;
import com.liren.model.Student;

import java.util.List;
import java.util.Scanner;

public class Demo7_select {
    public static void main(String[] args) {
        System.out.println("请输入班级名称：");
        Scanner sc = new Scanner(System.in);
        String classname = sc.nextLine();

        StudentDAO stda = new StudentDAO();
        List<Student> students = stda.selectByClassname(classname);
        for(Student s : students) {
            System.out.println(s);
        }
    }
}
