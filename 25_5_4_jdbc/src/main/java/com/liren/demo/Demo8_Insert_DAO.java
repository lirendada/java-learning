package com.liren.demo;

import com.liren.dao.ClassesDAO;
import com.liren.dao.StudentDAO;
import com.liren.model.Classes;

public class Demo8_Insert_DAO {
    public static void main(String[] args) {
        ClassesDAO cdao = new ClassesDAO();
        Classes cs = new Classes();
        cs.setId(4l);
        cs.setName("JDBC001班");
        cdao.insert(cs);
        System.out.println("Insert class success!");
    }
}
