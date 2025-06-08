package com.liren.dao;

import com.liren.model.Classes;
import com.liren.model.Student;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 专门针对学生表的数据库访问对象
 */
public class StudentDAO {
    // 增删改查。。。

    // 还有专门针对学生表的业务逻辑处理方法
    // 比如下面的：根据班级名查询班级中所有的同学信息
    public List<Student> selectByClassname(String classname) {
        if(classname == null || classname.isEmpty()) {
            System.out.println("班级名不能为空！");
            return null;
        }

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            List<Student> ret = new ArrayList<Student>();
            conn = DBUtil.getConnection();
            String sql = "select s.id, s.name, s.age, s.sno, s.gender, s.enroll_date, c.id c_id, c.name c_name from student s, class c where s.class_id = c.id and c.name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, classname);
            rs = ps.executeQuery();
            while(rs.next()) {
                // 封装Student对象
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setSno(rs.getString("sno"));
                s.setGender(rs.getByte("gender"));
                s.setEnroll_date(rs.getDate("enroll_date"));
                s.setClass_id(rs.getLong("c_id"));

                // 封装Classes对象
                Classes c = new Classes();
                c.setId(rs.getLong("c_id"));
                c.setName(rs.getString("c_name"));

                // 设置Student对象中的Classes对象，完成两者的关联
                s.setCs(c);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(rs, ps, conn);
        }
    }
}
