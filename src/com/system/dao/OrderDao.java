package com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDao {

    //买家查看订单
    public ResultSet buyerSeeOrder(Connection con, String usename) throws Exception {
        String sql = "SELECT * FROM B_order WHERE B_no=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, usename);
        return pstmt.executeQuery();
    }

    //卖家查看订单
    public ResultSet sellerSeeOrder(Connection con, String usename) throws Exception {
        String sql = "SELECT * FROM B_order WHERE S_no=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, usename);
        return pstmt.executeQuery();
    }

    //检查租房订单已提交
    public ResultSet chkRent(Connection con, String s, String s1, String s2) throws Exception//s=usename,s1=H_no,s2=S_no
    {
        String sql = "SELECT * FROM B_order WHERE B_no = ? AND S_no = ? AND H_no = ? AND order_stu = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, s);
        pstmt.setObject(2, s2);
        pstmt.setObject(3, s1);
        pstmt.setObject(4, "提交租房订单");
        return pstmt.executeQuery();
    }

    //检查买房订单已提交
    public ResultSet chkBuy(Connection con, String s, String s1, String s2) throws Exception//s=usename,s1=H_no,s2=S_no
    {
        String sql = "SELECT * FROM B_order WHERE B_no = ? AND S_no = ? AND H_no = ? AND order_stu = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, s);
        pstmt.setObject(2, s2);
        pstmt.setObject(3, s1);
        pstmt.setObject(4, "提交买房订单");
        return pstmt.executeQuery();
    }


    //提交租房订单
    public void selRent(Connection con, String s, String s1, String s2) throws Exception//s=usename,s1=H_no,s2=S_no
    {
        String sql = "INSERT INTO B_order(B_no,S_no,H_no,order_stu) VALUES(?,?,?,?);" +
                "UPDATE Houseinfo SET status=N'交易中' WHERE H_no=? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, s);
        pstmt.setObject(2, s2);
        pstmt.setObject(3, s1);
        pstmt.setObject(4, "提交租房订单");
        pstmt.setObject(5, s1);
        pstmt.execute();
    }

    //提交买房订单
    public void selBuy(Connection con, String s, String s1, String s2) throws Exception//s=usename,s1=H_no,s2=S_no
    {
        String sql = "INSERT INTO B_order(B_no,S_no,H_no,order_stu) VALUES(?,?,?,?);" +
                "UPDATE Houseinfo SET status=N'交易中' WHERE H_no=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, s);
        pstmt.setObject(2, s2);
        pstmt.setObject(3, s1);
        pstmt.setObject(4, "提交买房订单");
        pstmt.setObject(5, s1);
        pstmt.execute();
    }

    //职工查询交易视图
    public ResultSet DealList(Connection con) throws Exception {
        String sql = "SELECT * FROM Deal";
        PreparedStatement ps7 = con.prepareStatement(sql);
        return ps7.executeQuery();
    }
    
    //职工查询租房订单
    public ResultSet RentList(Connection con) throws Exception {
        String sql = "SELECT * FROM Deal where order_stu='提交租房订单'";
        PreparedStatement ps7 = con.prepareStatement(sql);
        return ps7.executeQuery();
    }
    
    //职工查询买房订单
    public ResultSet BuyList(Connection con) throws Exception {
        String sql = "SELECT * FROM Deal where order_stu='提交买房订单'";
        PreparedStatement ps7 = con.prepareStatement(sql);
        return ps7.executeQuery();
    }
}
