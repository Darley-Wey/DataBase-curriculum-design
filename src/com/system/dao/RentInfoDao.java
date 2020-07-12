package com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.system.model.Rentinfo;
import com.system.util.StringUtil;

public class RentInfoDao {

    //添加求租信息
    public static int rentInfoAdd(Connection con, Rentinfo rentinfo) throws Exception {
        String sql = "INSERT INTO Rent VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, rentinfo.getR_no());
        pstmt.setString(2, rentinfo.getB_no());
        pstmt.setString(3, rentinfo.getTel());
        pstmt.setString(4, rentinfo.getReg_ad());
        pstmt.setString(5, rentinfo.getMoney());
        pstmt.setString(6, rentinfo.getSty());
        pstmt.setString(7, rentinfo.getStatus());
        pstmt.setString(8, rentinfo.getAdd());
        return pstmt.executeUpdate();
    }

    //空查询所有，否则查询指定编号的信息
    public ResultSet rentinfoList(Connection con, Rentinfo rentinfo) throws Exception {
        if (StringUtil.isEmpty(rentinfo.getR_no())) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Rent");
            return pstmt.executeQuery();
        }
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Rent WHERE R_no=?");
        pstmt.setString(1, rentinfo.getR_no());
        return pstmt.executeQuery();
    }
}
