package com.liren.dao;

import com.liren.model.Classes;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 专门针对班级表的数据库查询对象
 */
public class ClassesDAO {
    public void insert(Classes cs) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into class(id, name) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cs.getId());
            ps.setString(2, cs.getName());
            int ret = ps.executeUpdate();
            if(ret > 0) {
                System.out.println("插入班级成功！");
            } else {
                System.out.println("插入班级失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(null, ps, conn);
        }
    }
}
