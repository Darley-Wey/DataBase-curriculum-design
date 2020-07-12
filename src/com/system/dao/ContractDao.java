package com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContractDao {

    //上传合同情况（管理员操作）
    public void contractAdd(Connection con, String s, String s1, String s2, String s3, String s4, String s5) throws Exception {
        String sql = "INSERT INTO contract (order_no,A_id,con_style,A_name,con_time,fee)VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, s);
        pstmt.setObject(2, s1);
        pstmt.setObject(3, s2);
        pstmt.setObject(4, s3);
        pstmt.setObject(5, s4);
        pstmt.setObject(6, s5);
        pstmt.execute();
    }

    //买家查看合同
    public ResultSet buyerSeeContract(Connection con, String usename) throws Exception {
        String sql = "SELECT order_no, con_style,contract.A_name AS A_name, con_time,  A_tel FROM contract,Admin WHERE contract.A_id=Admin.A_id AND " +
                "      order_no IN (SELECT order_no FROM B_order  WHERE B_no = ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, usename);
        return pstmt.executeQuery();
    }

    //卖家查看合同
    public ResultSet sellerSeeContract(Connection con, String usename) throws Exception {
        String sql = "SELECT order_no, con_style,contract.A_name AS A_name, con_time,  A_tel FROM contract,Admin WHERE contract.A_id=Admin.A_id AND " +
                "      order_no IN (SELECT order_no FROM B_order  WHERE S_no = ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, usename);
        return pstmt.executeQuery();

    }

    //分年月日出租出售营业额
    public ResultSet classTurnoverList(Connection con, String str1, String str2) throws Exception {
        str2 = str2 + "%";
        String sql = "SELECT con_style,sum(fee) fee FROM dbo.contract WHERE con_style=? AND con_time LIKE ? GROUP BY con_style";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setObject(1, str1);
        pst.setObject(2, str2);
        return pst.executeQuery();
    }


    //出租或出售营业额
    public ResultSet incomeList(Connection con, String str) throws Exception {
        String sql1 = "SELECT con_style,con_time,sum(fee) fee FROM dbo.contract WHERE con_style=?  GROUP BY con_style,con_time";
        PreparedStatement pst = con.prepareStatement(sql1);
        pst.setObject(1, str);
        return pst.executeQuery();
    }

    //全部营业额分类查询
    public ResultSet incomeList1(Connection con) throws Exception {
        String sql1 = "SELECT con_style,con_time,sum(fee) fee FROM dbo.contract  GROUP BY con_style,con_time";
        PreparedStatement pst = con.prepareStatement(sql1);
        //pst.setObject(1, str1);
        return pst.executeQuery();
    }

    //全部员工业绩
    public ResultSet revenueList(Connection con) throws Exception {
        String sql1 = "SELECT A_id,A_name,sum(fee) fee FROM contract GROUP BY A_id,A_name ORDER BY A_id";
        PreparedStatement pst = con.prepareStatement(sql1);
        return pst.executeQuery();
    }

    //分员工业绩
    public ResultSet revenueList1(Connection con, String A_id) throws Exception {
        String sql1 = "SELECT A_id,A_name,sum(fee) fee FROM contract WHERE A_id = ? GROUP BY A_id,A_name";
        PreparedStatement pst = con.prepareStatement(sql1);
        pst.setObject(1, A_id);
        return pst.executeQuery();
    }


}
