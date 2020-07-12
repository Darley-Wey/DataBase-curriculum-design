package com.system.view.buyer;

import com.system.dao.BuyerDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class BuyerOwnInfoFrm extends JFrame {

    private final JTable table;
    Jdbc jdbc=new Jdbc();
    BuyerDao buyerDao=new BuyerDao();
    private final String usename;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BuyerOwnInfoFrm frame = new BuyerOwnInfoFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */

    public BuyerOwnInfoFrm(String usename) {
        setTitle("查看个人信息");
        this.usename = usename;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setBounds(410, 155, 854, 467);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "用户号", "姓名", "密码", "电子邮箱", "地址", "联系电话"
                }
        ));
        scrollPane.setViewportView(table);
        this.fillTable();
    }

    public void fillTable() {
				/*
				  调用PreparedStatement用来调用connection.preparedstatemrnt(sql)
				  进行数据库对sql 的预编译处理
				 */
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        try (Connection con = jdbc.getConnection()) {
            ResultSet rs = buyerDao.BuyerList(con, usename);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("B_no"));
                //v.add(rs.getObject("B_id"));
                v.add(rs.getObject("B_name"));
                v.add(rs.getObject("B_password"));
                v.add(rs.getObject("B_email"));
                v.add(rs.getObject("B_add"));
                v.add(rs.getObject("B_tel"));
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


