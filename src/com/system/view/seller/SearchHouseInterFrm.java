package com.system.view.seller;

import com.system.dao.HouseInfoDao;
import com.system.model.Houseinfo;
import com.system.util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class SearchHouseInterFrm extends JInternalFrame {
    private final JTextField Txt;
    private final JTable table;
    DbUtil dbUtil = new DbUtil();
    HouseInfoDao houseinfoDao = new HouseInfoDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchHouseInterFrm frame = new SearchHouseInterFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SearchHouseInterFrm(String usename) {

    	setTitle("查询房源信息");
        setClosable(true);
        setBounds(10, 10, 1225, 600);
        getContentPane().setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 90, 1180, 450);
        getContentPane().add(scrollPane);
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "房源编号", "卖家号", "房源名称", "所属地址", "建筑单位", "朝向", "户型", "面积", "楼层", "单元号", "车库面积", "装修状况", "真实性", "交易状态", "出租价格(元/月)", "出售价格(万元)"
                }
        ));
        table.getColumnModel().getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.getColumnModel().getColumn(14).setPreferredWidth(125);
        table.getColumnModel().getColumn(15).setPreferredWidth(120);
        scrollPane.setViewportView(table);
//		重写一个点进去查询所有的函数

        JLabel label = new JLabel("房源编号：");
        label.setBounds(33, 30, 83, 40);
        getContentPane().add(label);

        Txt = new JTextField();
        Txt.setBounds(126, 30, 113, 40);
        getContentPane().add(Txt);
        Txt.setColumns(10);

        JButton button = new JButton("查询");//"查询"按钮的action
        button.addActionListener(e -> {
            String h_no = Txt.getText();
            Houseinfo houseinfo = new Houseinfo();
            houseinfo.setH_no(h_no);
            fillTable(houseinfo,usename);  //空查询所有，否则查询指定房源编号的信息
        });
        button.setBounds(297, 30, 95, 40);
        getContentPane().add(button);


    }

    /**
     * @param houseinfo 空查询所有，否则查询指定房源编号的信息
     */
    public void fillTable(Houseinfo houseinfo ,String usename) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con;
        try {
            con = dbUtil.getCon();
            ResultSet rs = houseinfoDao.houseInfoList(con, houseinfo,usename);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("H_no"));
                v.add(rs.getString("S_no"));
                v.add(rs.getString("H_name"));
                v.add(rs.getString("reg_ad"));
                v.add(rs.getString("Item_cop"));
                v.add(rs.getString("dir"));
                v.add(rs.getString("Stru_na"));
                v.add(rs.getString("area"));
                v.add(rs.getString("floor"));
                v.add(rs.getString("unit_no"));
                v.add(rs.getString("cararea"));
                v.add(rs.getString("fitment"));
                v.add(rs.getString("facticity"));
                v.add(rs.getString("status"));
                v.add(rs.getString("price"));
                v.add(rs.getString("money"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
