package com.liren.demo;

import utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo5_Delete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();

            String sql = "delete from class where name = ?";
            System.out.println("请输入要删除的班级名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            int ret = stmt.executeUpdate();
            if (ret > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(null, stmt, conn);
        }
    }
}
