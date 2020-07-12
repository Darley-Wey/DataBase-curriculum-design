package com.system.view.admin;

import com.system.dao.BuyerDao;
import com.system.model.SQLBuyer;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class BuyerInfo extends JInternalFrame {
    private final JTextField notext;
    private final JTable tableBuyer;
    Jdbc jdbcBean = new Jdbc();
    BuyerDao buyerDao = new BuyerDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BuyerInfo frame = new BuyerInfo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public BuyerInfo() {
    	setTitle("查看买家信息");
        setIconifiable(true);
        setClosable(true);
        setBounds(0, 0, 990, 490);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("买家用户号");
        lblNewLabel.setBounds(122, 13, 84, 44);
        getContentPane().add(lblNewLabel);

        notext = new JTextField();
        notext.setBounds(220, 22, 127, 27);
        getContentPane().add(notext);
        notext.setColumns(10);

        JScrollPane Buyerjsp = new JScrollPane();
        Buyerjsp.setBounds(50, 70, 850, 350);
        getContentPane().add(Buyerjsp);

        tableBuyer = new JTable();
        tableBuyer.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                		"买家用户号", "姓名", "电子邮箱", "地址", "联系电话"
                }
        ));
        tableBuyer.getColumnModel().getColumn(0).setPreferredWidth(97);
        tableBuyer.getColumnModel().getColumn(1).setPreferredWidth(85);
        tableBuyer.getColumnModel().getColumn(2).setPreferredWidth(83);
        tableBuyer.getColumnModel().getColumn(3).setPreferredWidth(137);
        tableBuyer.getColumnModel().getColumn(4).setPreferredWidth(102);
        Buyerjsp.setViewportView(tableBuyer);

        //条件查询事件
        JButton btnNewButton = new JButton("查询");
        btnNewButton.setIcon(new ImageIcon("img\\search.png"));
        btnNewButton.addActionListener(e -> {
            String b_no = notext.getText();
            SQLBuyer sqlBuyer = new SQLBuyer();
            sqlBuyer.setB_no(b_no);
            fillBuyer(sqlBuyer);
        });
        btnNewButton.setBounds(386, 21, 83, 28);
        getContentPane().add(btnNewButton);
        fillBuyer(new SQLBuyer());
    }

    public void fillBuyer(SQLBuyer sqlBuyer) {
        DefaultTableModel dtm3 = (DefaultTableModel) tableBuyer.getModel();
        dtm3.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            ResultSet rSet = buyerDao.BuyerLookList(con, sqlBuyer);
            while (rSet.next()) {
                Vector<Object> vs = new Vector<>();
                vs.add(rSet.getObject("B_no"));
                //vs.add(rSet.getObject("B_id"));
                vs.add(rSet.getObject("B_name"));
                //vs.add(rSet.getObject("B_password"));
                vs.add(rSet.getObject("B_email"));
                vs.add(rSet.getObject("B_add"));
                vs.add(rSet.getObject("B_tel"));
                dtm3.addRow(vs);
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
