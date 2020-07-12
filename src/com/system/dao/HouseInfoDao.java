package com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.system.model.SQLHouse;
import com.system.model.Houseinfo;
import com.system.util.StringUtil;

public class HouseInfoDao {

    //查询已审核未交易的房源信息
    public ResultSet houseList(Connection con) throws Exception {
        String sql = "SELECT * FROM Houseinfo WHERE status=N'未交易' AND facticity=N'已审核'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    //模糊查询房源信息
    public ResultSet houseCheck(Connection con, SQLHouse sqlHouse) throws Exception {
        StringBuilder sql = new StringBuilder("select * from Houseinfo");
        if (StringUtil.isNotEmpty(sqlHouse.getH_no())) {
            sql.append(" and H_no like '%").append(sqlHouse.getH_no()).append("%'");
        }
        PreparedStatement ps2 = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
        return ps2.executeQuery();
    }

    //条件查询房源
    public static ResultSet HouseList1(Connection con, Object s, Object s1, Object s2, Object s3, Object s4, Object s5) throws Exception {
        Object a = null, b = null, c = null, d, e, f;
        if (s == "") {
            a = "area is not null and";
        } else if (s == "50平以下") {
            a = "area < 50 and ";
        } else if (s == "50-150平") {
            a = "area >= 50 and area < 150 and ";
        } else if (s == "150-200平") {
            a = "area >= 150 and area <= 200 and ";
        } else if (s == "200平以上") {
            a = "area > 200 and ";
        }

        if (s1 == "") {
            d = "%";
        } else {
            d = s1;
        }

        if (s2 == "") {
            e = "%";
        } else {
            e = s2;
        }

        if (s3 == "") {
            b = " floor is not null and ";
        } else if (s3 == "1-6层") {
            b = " floor >= 1 and floor <= 6 and ";
        } else if (s3 == "7-16层") {
            b = " floor >= 7 and floor <= 16 and ";
        } else if (s3 == "17层以上") {
            b = " floor >= 17 and ";
        }

        if (s4 == "") {
            c = " money is not null and ";
        } else if (s4 == "100万以下") {
            c = " money >= 0 and money <100 and ";
        } else if (s4 == "100万-200万") {
            c = " money >= 100 and money < 200 and ";
        } else if (s4 == "200万以上") {
            c = " money >= 200 and ";
        }

        if (s5 == "") {
            f = "%";
        } else {
            f = s5;
        }
        String sql = "select * from Houseinfo where " + a + b + c + " dir like ? and  Stru_na like ? and fitment like ? and facticity='已审核' and status='未交易'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, d);
        pstmt.setObject(2, e);
        pstmt.setObject(3, f);
        return pstmt.executeQuery();
    }

    //审核房源信息
    public int houseVerify(Connection con, String hNo) throws Exception {
        String sql1 = "UPDATE Houseinfo SET facticity=N'已审核' WHERE H_no=?";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, hNo);
        return ps.executeUpdate();
    }

    //删除房源信息
    public int houseDelete(Connection con, String H_no) throws Exception {
        String sql1 = "DELETE FROM Houseinfo WHERE H_no=?";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, H_no);
        return ps.executeUpdate();
    }

    //添加房源信息
    public int houseinfoAdd(Connection con, Houseinfo houseinfo) throws Exception {
        String sql = "INSERT INTO Houseinfo VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, houseinfo.getH_no());
        pstmt.setString(2, houseinfo.getS_no());
        pstmt.setString(3, houseinfo.getH_name());
        pstmt.setString(4, houseinfo.getReg_ad());
        pstmt.setString(5, houseinfo.getItem_cop());
        pstmt.setString(6, houseinfo.getDir());
        pstmt.setString(7, houseinfo.getStru_na());
        pstmt.setString(8, houseinfo.getArea());
        pstmt.setString(9, houseinfo.getFloor());
        pstmt.setString(10, houseinfo.getUnit_no());
        pstmt.setString(11, houseinfo.getCararea());
        pstmt.setString(12, houseinfo.getFitment());
        pstmt.setString(13, houseinfo.getFacticity());
        pstmt.setString(14, houseinfo.getStatus());
        pstmt.setString(15, houseinfo.getPrice());
        pstmt.setString(16, houseinfo.getMoney());
        return pstmt.executeUpdate();
    }

    //查询指定卖家发布的房源信息，空查询所有，否则查询指定房源编号的信息
    public ResultSet houseInfoList(Connection con, Houseinfo houseinfo, String usename) throws Exception {
        if (StringUtil.isEmpty(houseinfo.getH_no())) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Houseinfo WHERE S_no=?");
            pstmt.setString(1, usename);
            return pstmt.executeQuery();
        }
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Houseinfo WHERE H_no=? AND S_no=?");
        pstmt.setString(1, houseinfo.getH_no());
        pstmt.setString(2, usename);
        return pstmt.executeQuery();
    }


    //签订合同改变房源信息中的房源状态
    public void changehouseinfo(Connection con, String s) throws Exception {
        int s1 = Integer.parseInt(s);
        String sql = "UPDATE Houseinfo SET status  =? WHERE H_no   COLLATE Chinese_PRC_CI_AS =(SELECT H_no FROM B_order WHERE order_no  =? ) ;"
                + "UPDATE B_order SET order_stu  =N'交易完成' WHERE order_no  =? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setObject(1, "已交易");
        pstmt.setObject(2, s1);
        pstmt.setObject(3, s1);
        pstmt.execute();
    }
}
