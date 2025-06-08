package com.liren.demo;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

/**
 * 使用DataSource连接数据库，使用PreparedStatement查询数据
 * @author liren
 * @date 2025-5-4
 */
public class Demo2_DataSource {
    public static void main(String[] args) {
        // 1. 设置mysql数据源的连接信息
        MysqlDataSource mds = new MysqlDataSource();
        mds.setURL("jdbc:mysql://localhost:3306/javalearning?useSSL=false&serverTimezone=UTC&characterEncoding=utf8");
        mds.setUser("root");
        mds.setPassword("123123");

        // 2. 向上转型成DataSource接口
        DataSource ds = mds;

        // 定义一些对象方便操作数据库
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 3. 获取数据库连接
            connection = ds.getConnection();

            // 4. 定义sql语句并创建PreparedStatement对象
            String sql = "select id, name, sno, gender, enroll_date, class_id from student where name = ?";
            statement = connection.prepareStatement(sql);

            // 获取输入参数
            System.out.println("请输入查询的用户名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();

            // 5. 进行占位符绑定
            statement.setString(1, name);

            // 6. 执行sql查询
            resultSet = statement.executeQuery();

            // 7. 处理结果集
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                name = resultSet.getString("name");
                String sno = resultSet.getString("sno");
                boolean gender = resultSet.getBoolean("gender");
                Date enrollDate = resultSet.getDate("enroll_date");
                Long classId = resultSet.getLong("class_id");
                System.out.println(id + "\t" + name + "\t" + sno + "\t" + gender + "\t" + enrollDate + "\t" + classId + "\t");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 8. 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
