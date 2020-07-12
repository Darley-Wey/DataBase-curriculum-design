package com.system.view.seller;

import com.system.dao.SellerDao;
import com.system.model.Seller;
import com.system.util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class SearchSellerInterFrm extends JInternalFrame {
    private final JTextField textField;
    private final JTable table;
    DbUtil dbUtil = new DbUtil();
    SellerDao sellerDao = new SellerDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchSellerInterFrm frame = new SearchSellerInterFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SearchSellerInterFrm(String usename) {
        setTitle("查询个人信息");
        setClosable(true);
        setBounds(100, 100, 833, 406);
        getContentPane().setLayout(null);
//		重写一个点进去查询所有的函数

        JLabel lblNewLabel = new JLabel("卖家用户号：");
        lblNewLabel.setBounds(51, 34, 93, 30);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(146, 31, 118, 33);
        textField.setEditable(false);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.setText(usename);

        JButton button = new JButton("查询");//"查询"按钮的action
        button.addActionListener(e -> {
            String s_no = textField.getText();
            Seller seller = new Seller();
            seller.setS_no(s_no);
            fillTable(seller);  //空查询所有，否则查询指定卖家注册号的信息
        });
        button.setBounds(319, 30, 93, 34);
        getContentPane().add(button);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(51, 96, 712, 230);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "用户号", "姓名", "密码", "电子邮箱", "地址", "联系电话"
                }
        ));
        scrollPane.setViewportView(table);

    }

    public void fillTable(Seller seller) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con;
        try {
            con = dbUtil.getCon();
            ResultSet rs = sellerDao.sellerList(con, seller);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("S_no"));
                //v.add(rs.getString("S_id"));
                v.add(rs.getString("S_name"));
                v.add(rs.getString("S_password"));
                v.add(rs.getString("S_email"));
                v.add(rs.getString("S_add"));
                v.add(rs.getString("S_tel"));
                dtm.addRow(v);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
