package com.system.dao;

import com.system.model.SQLAdmin;
import jdbc.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class AdminDao {
    Jdbc jdbc = new Jdbc();
    Connection conn = jdbc.getConnection();

    //职工登录
    public boolean validateAdmin(String id, String password) {
        String sql = "select * from Admin where Admin.A_id='" + id + "'and Admin.A_password='" + password + "'";
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

    //检查职工号是否已注册
    private boolean validateAdminID(String id) {
        String sql = "select *from Admin where Admin.A_id='" + id + "'";
        try (
                Statement pstmt = conn.createStatement();
                ResultSet rs = pstmt.executeQuery(sql)) {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //职工注册
    public String cheakAdmin(String A_id, String A_password, String A_name, String A_tel) throws Exception {
        if (validateAdmin(A_id, A_password)) {
            return "账号已存在";
        } else if (validateAdminID(A_id)) {
            return "账号已被注册";
        } else {
            String sql = "INSERT INTO Admin(A_id,A_password,A_name,A_tel) VALUES(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, A_id);
            pstmt.setString(2, A_password);
            pstmt.setString(3, A_name);
            pstmt.setString(4, A_tel);
            pstmt.executeUpdate();
        }
        return "注册成功";
    }


    //职工信息查询
    public ResultSet AdminList(Connection con, String id) throws Exception {
        String sql = "SELECT * FROM Admin WHERE A_id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, id);
        return st.executeQuery();
    }

    //职工数据更新
    public int AdmUpdate(Connection con, SQLAdmin sqlAdmin) throws Exception {
        String sql2 = "UPDATE Admin SET A_name=?,A_tel=?,A_password=? WHERE A_id=?";
        PreparedStatement pst2 = con.prepareStatement(sql2);
        pst2.setString(1, sqlAdmin.getName());
        pst2.setString(2, sqlAdmin.getTelphone());
        pst2.setString(3, sqlAdmin.getPassword());
        pst2.setString(4, sqlAdmin.getId());
        return pst2.executeUpdate();
    }
}