package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Darley
 */
public class Jdbc {
    private static final String dir = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://;DatabaseName=";//数据库地址
    private static final String user = "";
    private static final String password = "";
    public static Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName(dir);//加载数据库驱动
            conn = DriverManager.getConnection(URL, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean checkSql() {
        try {
            conn = DriverManager.getConnection(URL, user, password);
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    public void closeCt(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
