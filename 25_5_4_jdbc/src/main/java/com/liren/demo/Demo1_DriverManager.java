package com.liren.demo;

import java.sql.*;

/**
 * 使用DriverManager获取数据库连接，使用Statement对象执行sql语句查询学生信息
 * @author liren
 * @date 2025-5-4
 */
public class Demo1_DriverManager {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. 加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalearning?useSSL=false&serverTimezone=UTC&characterEncoding=utf8",
                    "root", "123123");

            // 3. 创建Statement对象
            statement = connection.createStatement();

            // 4. 执行sql语句
            String sql = "select id, name, sno, gender, enroll_date, class_id from student";
            resultSet = statement.executeQuery(sql);

            // 5. 处理结果集
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String sno = resultSet.getString("sno");
                boolean gender = resultSet.getBoolean("gender");
                Date enrollDate = resultSet.getDate("enroll_date");
                Long classId = resultSet.getLong("class_id");
                System.out.println(id + "\t" + name + "\t" + sno + "\t" + gender + "\t" + enrollDate + "\t" + classId + "\t");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 6. 释放资源
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
