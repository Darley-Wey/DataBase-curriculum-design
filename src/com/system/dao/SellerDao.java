package com.system.dao;

import java.sql.*;

import com.system.model.SQLSeller;
import com.system.model.Seller;
import com.system.util.StringUtil;
import jdbc.Jdbc;

public class SellerDao {
    Jdbc jdbc = new Jdbc();
    Connection conn = jdbc.getConnection();


    //卖家登录
    public boolean validateSeller(String no, String password) {

        String sql = "select * from Seller where Seller.S_no='" + no + "'and Seller.S_password='" + password + "'";
        try (
                Statement pstmt = conn.createStatement();
                ResultSet rs = pstmt.executeQuery(sql)) {    //如果查询的ResultSet里有超过一条的记录，则登录成功
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //卖家注册
    public String cheakSeller(String no, String name, String password, String email, String add, String tel) throws Exception {
        if (validateSeller(no, password)) {
            return "账号已存在";
        } else if (validateSellerID(no)) {
            return "账号已被注册";
        } else {
            String sql = "INSERT INTO Seller(S_no,S_name,S_password,S_email,S_add,S_tel) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, no);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setString(5, add);
            pstmt.setString(6, tel);
            pstmt.executeUpdate();
            return "注册成功";
        }
    }

    //检查卖家号已注册
    private boolean validateSellerID(String no) {
        String sql = "select *from Seller where Seller.S_no='" + no + "'";
        try (
                Statement pstmt = conn.createStatement();
                ResultSet rs = pstmt.executeQuery(sql)) {    //如果查询的ResultSet里有超过一条的记录，则登录成功
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


    //空查询所有，否则查询指定卖家号的信息
    public ResultSet sellerList(Connection con, Seller seller) throws Exception {
        if (StringUtil.isEmpty(seller.getS_no())) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Seller");
            return pstmt.executeQuery();
        }
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Seller WHERE S_no=?");
        pstmt.setString(1, seller.getS_no());
        return pstmt.executeQuery();
    }

    //模糊查询卖家信息
    public ResultSet SellerList(Connection con, SQLSeller sqlSeller) throws Exception {
        StringBuilder sql2 = new StringBuilder("select * from Seller");
        if (StringUtil.isNotEmpty(sqlSeller.getS_no())) {
            sql2.append(" and s_no like '%").append(sqlSeller.getS_no()).append("%'");
        }
        PreparedStatement ps8 = con.prepareStatement(sql2.toString().replaceFirst("and", "where"));
        return ps8.executeQuery();
    }

    //删除卖家
  /*  public int sellerDelete(Connection con, String s_no) throws Exception {

        PreparedStatement pstmt = con.prepareStatement("DELETE  FROM Seller WHERE S_no=?");
        pstmt.setString(1, s_no);
        return pstmt.executeUpdate();
    }*/

    //修改卖家信息
    public int sellerModify(Connection con, Seller seller) throws Exception {
        String sql = "UPDATE Seller SET S_name=?,S_password=?,S_email=?,S_add=?,S_tel=? WHERE S_no=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, seller.getS_name());
        pstmt.setString(2, seller.getS_password());
        pstmt.setString(3, seller.getS_email());
        pstmt.setString(4, seller.getS_add());
        pstmt.setString(5, seller.getS_tel());
        pstmt.setString(6, seller.getS_no());
        return pstmt.executeUpdate();
    }
}
