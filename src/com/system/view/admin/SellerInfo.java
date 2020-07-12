package com.system.view.admin;

import com.system.dao.SellerDao;
import com.system.model.SQLSeller;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class SellerInfo extends JInternalFrame {
    private final JTable tableSeller;
    private final JTextField Snotext;

    Jdbc jdbcBean = new Jdbc();
    SellerDao sellerDao = new SellerDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SellerInfo frame = new SellerInfo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SellerInfo() {
    	setTitle("查看卖家信息");
        setIconifiable(true);
        setClosable(true);
        setBounds(0, 0, 990, 490);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 70, 850, 350);
        getContentPane().add(scrollPane);

        tableSeller = new JTable();
        tableSeller.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "卖家用户号", "姓名", "电子邮箱", "地址", "联系电话"
                }
        ));
        tableSeller.getColumnModel().getColumn(0).setPreferredWidth(122);
        tableSeller.getColumnModel().getColumn(1).setPreferredWidth(84);
        tableSeller.getColumnModel().getColumn(2).setPreferredWidth(117);
        tableSeller.getColumnModel().getColumn(3).setPreferredWidth(137);
        tableSeller.getColumnModel().getColumn(4).setPreferredWidth(111);
        scrollPane.setViewportView(tableSeller);

        JLabel lblNewLabel = new JLabel("输入卖家用户号");
        lblNewLabel.setBounds(159, 24, 113, 25);
        getContentPane().add(lblNewLabel);

        Snotext = new JTextField();
        Snotext.setBounds(286, 22, 151, 28);
        getContentPane().add(Snotext);
        Snotext.setColumns(10);

        JButton Snobutton = new JButton("查询");
        Snobutton.setIcon(new ImageIcon("img\\search.png"));
        Snobutton.addActionListener(e -> {
            String s_no = Snotext.getText();
            SQLSeller sqlSeller = new SQLSeller();
            sqlSeller.setS_no(s_no);
            fillSeller(sqlSeller);
        });
        Snobutton.setBounds(456, 22, 83, 29);
        getContentPane().add(Snobutton);
        fillSeller(new SQLSeller());
    }

    public void fillSeller(SQLSeller sqlSeller) {
        DefaultTableModel dtm4 = (DefaultTableModel) tableSeller.getModel();
        dtm4.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            ResultSet rs2 = sellerDao.SellerList(con, sqlSeller);
            while (rs2.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs2.getObject("S_no"));
                //v.add(rs2.getObject("S_id"));
                v.add(rs2.getObject("S_name"));
                //v.add(rs2.getObject("S_password"));
                v.add(rs2.getObject("S_email"));
                v.add(rs2.getObject("S_add"));
                v.add(rs2.getObject("S_tel"));
                //v.add(rs2.getObject("order"));
                dtm4.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(con);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
