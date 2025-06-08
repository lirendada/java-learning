package com.liren.demo;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
 * 通过班级名称查询学生信息
 */
public class Demo6_SelectInfoByClassname {
    public static void main(String[] args) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            // 获取连接
            conn = DBUtil.getConnection();

            // 定义sql语句
            String sql = "select s.id, s.name, s.age, s.sno, s.gender, s.enroll_date, c.id c_id, c.name c_name from student s, class c where s.class_id = c.id and c.name = ?";
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入班级名称：");
            String className = sc.nextLine();

            // 创建PreparedStatement对象，并设置占位符
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, className);

            // 执行查询
            rs = stmt.executeQuery();

            // 处理结果集
            while(rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sno = rs.getString("sno");
                byte gender = rs.getByte("gender");
                Date enroll_date = rs.getDate("enroll_date");
                long cid = rs.getLong("c_id");
                String cname = rs.getString("c_name");
                System.out.println("id=" + id + ", name=" + name + ", age=" + age + ", sno=" + sno + ", gender=" + gender + ", enroll_date=" + enroll_date + ", class_id=" + cid + ", class_name=" + cname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(rs, stmt, conn);
        }
    }
}
