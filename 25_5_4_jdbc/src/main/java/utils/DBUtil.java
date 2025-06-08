package utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/javalearning?useUnicode=true&characterEncoding=utf8";
    private static String user = "root";
    private static String password = "123123";

    private static DataSource ds = null;

    // 在类加载的时候初始化dataSource对象
    static {
        MysqlDataSource mds = new MysqlDataSource();
        mds.setURL(url);
        mds.setUser(user);
        mds.setPassword(password);
        ds = mds;
    }

    // 构造方法私有化，防止其他的地方通过new来创建这个类的对象
    private DBUtil() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnection(ResultSet resultSet, Statement statement, Connection conn) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
