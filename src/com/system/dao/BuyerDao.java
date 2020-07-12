package com.system.dao;

import com.system.model.SQLBuyer;
import com.system.util.StringUtil;
import jdbc.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyerDao {
    Jdbc jdbc = new Jdbc();
    Connection conn = jdbc.getConnection();

    //买家登录
    public boolean validateBuyer(String no, String password) {
        String sql = "select *from Buyer where Buyer.B_no='" + no + "'and Buyer.B_password='" + password + "'";
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

    //买家注册
    public String cheakBuyer(String no, String name, String password, String email, String add, String tel) throws Exception {
        if (validateBuyer(no, password)) {
            return "账号已存在";
        } else if (validatebuyerid(no)) {
            return "账号已被注册";
        } else {
            String sql = "INSERT INTO Buyer(B_no,B_name,B_password,B_email,B_add,B_tel) VALUES(?,?,?,?,?,?)";
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

    //检查买家号已注册
    private boolean validatebuyerid(String no) {
        String sql = "select *from Buyer where Buyer.B_no='" + no + "'";
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


    //查询买家个人信息
    public ResultSet BuyerList(Connection con, String usename) throws Exception {
        String sql = "SELECT * FROM Buyer WHERE B_no=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, usename);
        return pstmt.executeQuery();
    }

    //模糊查询买家信息
    public ResultSet BuyerLookList(Connection con, SQLBuyer sqlBuyer) throws Exception {
        StringBuilder sql = new StringBuilder("select * from Buyer");
        if (StringUtil.isNotEmpty(sqlBuyer.getB_no())) {
            sql.append(" and B_no like '%").append(sqlBuyer.getB_no()).append("%'");
        }
        PreparedStatement ps7 = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
        return ps7.executeQuery();
    }

    //修改买家信息
    public int buyerModify(Connection con, String bName, String bPass, String bEmail, String bAddr, String bTel, String usename) throws Exception {
        if (!bPass.equals("")) {
            String sql = "UPDATE Buyer SET B_password=? WHERE B_no=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, bPass);
            pstmt.setObject(2, usename);
            pstmt.execute();
        }
        if (bName != null && !bName.equals("")) {
            String sql1 = "UPDATE Buyer SET B_name=? WHERE B_no=?";
            PreparedStatement pstmt1 = con.prepareStatement(sql1);
            pstmt1.setObject(1, bName);
            pstmt1.setObject(2, usename);
            pstmt1.execute();
        }
        if (bEmail != null && !bEmail.equals("")) {
            String sql2 = "UPDATE Buyer SET B_email=? WHERE B_no=?";
            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setObject(1, bEmail);
            pstmt2.setObject(2, usename);
            pstmt2.execute();
        }
        if (bAddr != null && !bAddr.equals("")) {
            String sql3 = "UPDATE Buyer SET B_add=? WHERE B_no=?";
            PreparedStatement pstmt3 = con.prepareStatement(sql3);
            pstmt3.setObject(1, bAddr);
            pstmt3.setObject(2, usename);
            pstmt3.execute();
        }
        if (bTel != null && !bTel.equals("")) {
            String sql4 = "UPDATE Buyer SET B_tel=? WHERE B_no=?";
            PreparedStatement pstmt4 = con.prepareStatement(sql4);
            pstmt4.setObject(1, bTel);
            pstmt4.setObject(2, usename);
            pstmt4.execute();
        }
        return 1;
    }

}

//删除买家信息
	/*public int Buyerdele(Connection con,String B_no)throws Exception {
		String sql="delete from Buyer where B_no=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, B_no);
		return pst.executeUpdate();
	}
}*/
