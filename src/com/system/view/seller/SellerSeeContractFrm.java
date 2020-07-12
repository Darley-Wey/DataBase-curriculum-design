package com.system.view.seller;

import com.system.dao.ContractDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

//import util.dao;

public class SellerSeeContractFrm extends JFrame {

    private final JTable table;
    Jdbc jdbc=new Jdbc();
    ContractDao contractDao=new ContractDao();
    private final String usename;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	SellerSeeContractFrm frame = new SellerSeeContractFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SellerSeeContractFrm(String usename) {
        setTitle("查看合同信息");
        this.usename = usename;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setBounds(410, 155, 605, 490);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "订单号", "交易进度", "合同类型", "中介姓名", "签订时间", "中介电话"
                }
        ));
        scrollPane.setViewportView(table);
        this.fillTable();
    }

    public void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        try (Connection con = jdbc.getConnection()) {
            ResultSet rs = contractDao.sellerSeeContract(con, usename);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("order_no"));
                v.add("交易完成");
                v.add(rs.getObject("con_style"));
                v.add(rs.getObject("A_name"));
                v.add(rs.getObject("con_time"));
                v.add(rs.getObject("A_tel"));
                dtm.addRow(v);
            }
            Vector<Object> v = new Vector<>();
            for (int i = 0; i < 30; i++) {
                dtm.addRow(v);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO: handle exception
    }

}
