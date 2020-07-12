package com.system.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Darley
 */
public class DbUtil {
    String dbUrl = "jdbc:sqlserver://47.94.86.203:1433;DatabaseName=HousingAgency";//数据库地址
    String userName = "sa";
    String userPwd = "Keshe001";
    String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动

    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        return DriverManager.getConnection(dbUrl, userName, userPwd);
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
