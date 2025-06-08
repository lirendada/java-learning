package com.liren.demo;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 结合DBUtil，简化代码，实现插入数据
 * @author liren
 * @date 2025/5/4 16:30
 */
public class Demo3_Insert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 1. 获取连接
            conn = DBUtil.getConnection();

            // 2. 创建SQL语句
            String sql = "insert into class (name) values(?)";

            // 3. 输入参数，绑定占位符
            System.out.println("请输入班级名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            // 4. 执行sql
            int ret = stmt.executeUpdate();
            if(ret > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 关闭资源
            DBUtil.closeConnection(null, stmt, conn);
        }
    }
}
