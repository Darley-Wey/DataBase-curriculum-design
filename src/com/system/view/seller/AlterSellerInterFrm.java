package com.system.view.seller;

import com.system.dao.SellerDao;
import com.system.model.Seller;
import com.system.util.DbUtil;
import com.system.util.StringUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;

public class AlterSellerInterFrm extends JInternalFrame {
    DbUtil dbUtil = new DbUtil();
    SellerDao sellerDao = new SellerDao();
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_4;
    private final JTextField textField_5;
    private final JTextField textField_6;
    private final JTextField textField_7;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AlterSellerInterFrm frame = new AlterSellerInterFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public AlterSellerInterFrm(String usename) {
        setClosable(true);
        setTitle("修改个人信息");
        setBounds(100, 100, 528, 430);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "个人信息操作", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setToolTipText("");
        panel.setBounds(36, 61, 438, 292);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("用户号：");
        lblNewLabel_1.setBounds(26, 35, 60, 23);
        panel.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(90, 35, 129, 30);
        panel.add(textField_1);
        textField_1.setColumns(10);
        textField_1.setText(usename);

        JLabel label = new JLabel("姓名：");
        label.setBounds(26, 78, 54, 23);
        panel.add(label);

        textField_2 = new JTextField();
        textField_2.setBounds(90, 78, 129, 30);
        panel.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("密码：");
        lblNewLabel_2.setBounds(26, 246, 54, 37);
        panel.add(lblNewLabel_2);

        textField_4 = new JTextField();
        textField_4.setBounds(90, 249, 129, 30);
        panel.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblEmail = new JLabel("Email：");
        lblEmail.setBounds(26, 121, 60, 23);
        panel.add(lblEmail);

        textField_5 = new JTextField();
        textField_5.setBounds(90, 121, 129, 30);
        panel.add(textField_5);
        textField_5.setColumns(10);

        JLabel label_1 = new JLabel("地址：");
        label_1.setBounds(26, 163, 54, 23);
        panel.add(label_1);

        textField_6 = new JTextField();
        textField_6.setBounds(90, 163, 129, 30);
        panel.add(textField_6);
        textField_6.setColumns(10);

        JLabel label_2 = new JLabel("电话：");
        label_2.setBounds(26, 206, 54, 23);
        panel.add(label_2);

        textField_7 = new JTextField();
        textField_7.setBounds(90, 206, 129, 30);
        panel.add(textField_7);
        textField_7.setColumns(10);

        JButton jb_motify = new JButton("修改");
        jb_motify.addActionListener(arg0 -> {

            String s_no = textField_1.getText();
            // String s_id=textField_3.getText();
            String s_name = textField_2.getText();
            String s_password = textField_4.getText();
            String s_email = textField_5.getText();
            String s_add = textField_6.getText();
            String s_tel = textField_7.getText();


            if (StringUtil.isEmpty(s_name)) {
                JOptionPane.showMessageDialog(null,
                        "请输入姓名！");
                return;
            }
            if (StringUtil.isEmpty(s_password)) {
                JOptionPane.showMessageDialog(null,
                        "请输入密码！");
                return;
            }
            if (StringUtil.isEmpty(s_email)) {
                JOptionPane.showMessageDialog(null,
                        "请输入电子邮箱！");
                return;
            }
            if (StringUtil.isEmpty(s_add)) {
                JOptionPane.showMessageDialog(null,
                        "请输入地址！");
                return;
            }
            if (StringUtil.isEmpty(s_tel)) {
                JOptionPane.showMessageDialog(null,
                        "请输入电话！");
                return;
            }

            Seller seller = new Seller(s_no, s_name, s_password, s_email, s_add, s_tel);
            Connection con;
            try {
                con = dbUtil.getCon();
                int Num = sellerDao.sellerModify(con, seller);
                if (Num == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    resetValue(usename);
                    // fillTable(new Seller());
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        });
        jb_motify.setBounds(294, 110, 91, 34);
        panel.add(jb_motify);

    }

    public void resetValue(String usename) {
        textField_1.setText(usename);
        textField_2.setText("");
        // textField_3.setText("");
        textField_4.setText("");
        textField_5.setText("");
        textField_6.setText("");
        textField_7.setText("");

    }
 
}
