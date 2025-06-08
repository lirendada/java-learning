package com.liren.demo;

import utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo4_Update {
    public static void main(String[] args) {
        // 1. 创建数据源
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 1. 获取连接
            conn = DBUtil.getConnection();

            // 2. 创建sql语句
            String sql = "update student set age = ?, gender = ? where name = ?";

            // 3. 输入参数，绑定占位符
            System.out.println("请输入名称：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            System.out.println("请输入要更新的年龄：");
            int age = sc.nextInt();
            System.out.println("请输入要更新的性别：");
            byte gender = sc.nextByte();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, age);
            stmt.setByte(2, gender);
            stmt.setString(3, name);

            // 4. 执行sql
            int ret = stmt.executeUpdate();
            if(ret > 0) {
                System.out.println("更新成功！");
            } else {
                System.out.println("更新失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 释放资源
            DBUtil.closeConnection(null, stmt, conn);
        }
    }
}
