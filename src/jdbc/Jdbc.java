package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Darley
 */
public class Jdbc {
    private static final String dir = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://47.94.86.203:1433;DatabaseName=HousingAgency";
    private static final String user = "sa";
    private static final String password = "Keshe001";
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
