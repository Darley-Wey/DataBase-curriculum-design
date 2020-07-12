package com.system.view;

import com.system.dao.AdminDao;
import com.system.dao.BuyerDao;
import com.system.dao.SellerDao;
import com.system.util.MyLister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegFrame {
    private final JFrame jf = new JFrame("注册");
    private final JButton adminButton = new JButton("职工注册");
    private final JButton sellerButton = new JButton("卖家注册");
    private final JButton buyerButton = new JButton("买家注册");
    private final JButton exitButton = new JButton("退出");
    AdminDao adminDao=new AdminDao();
    BuyerDao buyerDao=new BuyerDao();
    SellerDao sellerDao=new SellerDao();


    public void Init() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        jf.getContentPane().setLayout(null);
        adminButton.setBounds(218, 41, 93, 35);
        jf.getContentPane().add(adminButton);

        adminButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                closeThis();
                regAdmin();
            }

            private void closeThis() {
                // TODO Auto-generated method stub
                jf.dispose();
            }

        });
        sellerButton.setBounds(218, 106, 93, 35);
        jf.getContentPane().add(sellerButton);

        sellerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                closeThis();
                regSeller();
            }

            private void closeThis() {
                // TODO Auto-generated method stub
                jf.dispose();
            }

        });
        buyerButton.setBounds(218, 171, 93, 35);
        jf.getContentPane().add(buyerButton);
        exitButton.setBounds(218, 236, 93, 35);
        exitButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent arg0) {
        		 // TODO Auto-generated method stub
                try {
                    jf.dispose();
                    new LoginFrm().Init();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        	}
            });
        
        
        jf.getContentPane().add(exitButton);
        buyerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                closeThis();
                regBuyer();
            }

            private void closeThis() {
                // TODO Auto-generated method stub
                jf.dispose();
            }

        });
        jf.setVisible(true);
        jf.setSize(542, 355);
        jf.setLocationRelativeTo(null);

    }

    //职工注册界面
    public void regAdmin() {
        JFrame jf1 = new JFrame("职工注册");
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JTextField adIDField = new JTextField(20);
        JTextField adNameField = new JTextField(20);
        JTextField adTelField = new JTextField(20);
        JTextField adPassField = new JTextField(20);
        adPassField.setToolTipText("密码长度最少为6位字符串");
        JButton adminRegButton = new JButton("注册");
        JButton returnButton = new JButton("返回");
        jp1.add(new JLabel("职工号:"));
        jp1.add(adIDField);
        jp2.add(new JLabel("姓    名:"));
        jp2.add(adNameField);
        jp3.add(new JLabel("电    话:"));
        jp3.add(adTelField);
        jp4.add(new JLabel("密    码:"));
        jp4.add(adPassField);
        jp5.add(adminRegButton);
        jp5.add(returnButton);
        jp6.add(jp2, BorderLayout.NORTH);
        jp6.add(jp3, BorderLayout.CENTER);
        jp6.add(jp4, BorderLayout.SOUTH);
        jf1.getContentPane().add(jp1, BorderLayout.NORTH);
        jf1.getContentPane().add(jp6, BorderLayout.CENTER);
        jf1.getContentPane().add(jp5, BorderLayout.SOUTH);
        jf1.addWindowListener(new MyLister());
        jf1.setSize(500, 300);
        jf1.setLocationRelativeTo(null);
        jf1.setVisible(true);

        returnButton.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                jf1.dispose();
                new LoginFrm().Init();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        adminRegButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                if (adIDField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(jf, "职工号不能为空");
                    return;
                }
                if (adNameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(jf, "姓名不能为空");
                    return;
                }
                if (adTelField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(jf, "电话不能为空");
                    return;
                }
                if (adPassField.getText().length() < 6) {
                    JOptionPane.showMessageDialog(jf, "密码长度最少为6位字符串");
                    return;
                }
                try {

                    if(adminDao.cheakAdmin(adIDField.getText(), adPassField.getText(), adNameField.getText(), adTelField.getText())=="账号已存在") {
                        JOptionPane.showMessageDialog(jf, "您已经有账号了请直接登录");
                    }
                    else if(adminDao.cheakAdmin(adIDField.getText(), adPassField.getText(), adNameField.getText(), adTelField.getText())=="账号已被注册") {
                        JOptionPane.showMessageDialog(jf, "该账号已被注册");
                    }
                    else {JOptionPane.showMessageDialog(jf, "注册成功请登录");}
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }




        });

    }

    //买家注册界面
    public void regBuyer() {
        JFrame jf2 = new JFrame("买家注册");
        JPanel jp1 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JPanel jp7 = new JPanel();
        JPanel jp8 = new JPanel();
        JPanel jp9 = new JPanel();
        JPanel jp10 = new JPanel();
        JPanel jp11 = new JPanel();
        JButton returnButton = new JButton("返回");
        JButton buyerRegButton = new JButton("注册");
        JTextField buyNoField = new JTextField(20);
        JTextField buyNameField = new JTextField(20);
        JTextField buyPassField = new JTextField(20);
        buyPassField.setToolTipText("密码长度最少为6位字符串");
        JTextField buyEmailField = new JTextField(11);
        JTextField buyAddField = new JTextField(20);
        JTextField buyTelField = new JTextField(20);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("@163.com");
        comboBox.addItem("@126.com");
        comboBox.addItem("@outlook.com");
        comboBox.addItem("@qq.com");

        jp1.add(new JLabel("用户号:"));
        jp1.add(buyNoField);
       // jp1.setFont(new Font("MS Song", Font.PLAIN, 18));
        jp3.add(new JLabel("姓名:"));
        jp3.add(buyNameField);
        jp4.add(new JLabel("密码:"));
        jp4.add(buyPassField);
        jp5.add(new JLabel("邮箱:"));
        jp5.add(buyEmailField);
        jp5.add(comboBox);
        jp6.add(new JLabel("地址:"));
        jp6.add(buyAddField);
        jp7.add(new JLabel("电话:"));
        jp7.add(buyTelField);
        jp8.add(buyerRegButton);
        jp8.add(returnButton);
        jp9.add(jp1);
        jp9.add(jp3);
        jp10.add(jp4);
        jp10.add(jp5);
        jp10.add(jp6);
        jp11.add(jp10, BorderLayout.NORTH);
        jp11.add(jp7, BorderLayout.SOUTH);
        jf2.getContentPane().add(jp9, BorderLayout.NORTH);
        jf2.getContentPane().add(jp11, BorderLayout.CENTER);
        jf2.getContentPane().add(jp8, BorderLayout.SOUTH);
        jf2.setSize(1000, 300);
        jf2.setLocationRelativeTo(null);
        jf2.setVisible(true);

        returnButton.addActionListener(e -> {
            /* TODO Auto-generated method stub */
            try {
                jf2.dispose();
                new LoginFrm().Init();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        buyerRegButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (getText(buyNoField, buyNameField, buyPassField, buyEmailField, buyAddField, buyTelField)) {
                    return;
                }


                try {
                    if(buyerDao.cheakBuyer(buyNoField.getText(), buyNameField.getText(), buyPassField.getText(), buyEmailField.getText() + comboBox.getSelectedItem(), buyAddField.getText(), buyTelField.getText())=="账号已存在") {
                        JOptionPane.showMessageDialog(jf, "您已经有账号了请直接登录");
                    }
                    else if(buyerDao.cheakBuyer(buyNoField.getText(), buyNameField.getText(), buyPassField.getText(), buyEmailField.getText() + comboBox.getSelectedItem(), buyAddField.getText(), buyTelField.getText())=="账号已被注册") {
                        JOptionPane.showMessageDialog(jf, "该账号已被注册。");
                    }
                    else {
                        JOptionPane.showMessageDialog(jf, "注册成功请登录");
                    }

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }


        });


    }


//卖家注册界面
    public void regSeller() {
        JFrame jf3 = new JFrame("卖家注册");
        JPanel jp1 = new JPanel();
        //JPanel jp2=new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JPanel jp7 = new JPanel();
        JPanel jp8 = new JPanel();
        JPanel jp9 = new JPanel();
        JPanel jp10 = new JPanel();
        JPanel jp11 = new JPanel();
        JButton returnButton = new JButton("返回");
        JButton sellerRegButton = new JButton("注册");
        JTextField sellNoField = new JTextField(20);
        JTextField sellNameField = new JTextField(20);
        JTextField sellPassField = new JTextField(20);
        sellPassField.setToolTipText("密码长度最少为6位字符串");
        JTextField sellEmailField = new JTextField(11);
        JTextField sellAddField = new JTextField(20);
        JTextField sellTelField = new JTextField(20);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("@163.com");
        comboBox.addItem("@126.com");
        comboBox.addItem("@outlook.com");
        comboBox.addItem("@qq.com");

        jp1.add(new JLabel("用户号:"));
        jp1.add(sellNoField);
        jp3.add(new JLabel("姓名:"));
        jp3.add(sellNameField);
        jp4.add(new JLabel("密码:"));
        jp4.add(sellPassField);
        jp5.add(new JLabel("邮箱:"));
        jp5.add(sellEmailField);
        jp5.add(comboBox);
        jp6.add(new JLabel("地址:"));
        jp6.add(sellAddField);
        jp7.add(new JLabel("电话:"));
        jp7.add(sellTelField);
        jp8.add(sellerRegButton);
        jp8.add(returnButton);
        jp9.add(jp1);
        jp9.add(jp3);
        jp10.add(jp4);
        jp10.add(jp5);
        jp10.add(jp6);
        jp11.add(jp10, BorderLayout.NORTH);
        jp11.add(jp7, BorderLayout.SOUTH);
        jf3.getContentPane().add(jp9, BorderLayout.NORTH);
        jf3.getContentPane().add(jp11, BorderLayout.CENTER);
        jf3.getContentPane().add(jp8, BorderLayout.SOUTH);
        jf3.setSize(1000, 300);
        jf3.setLocationRelativeTo(null);
        jf3.setVisible(true);

        returnButton.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                jf3.dispose();
                new LoginFrm().Init();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        sellerRegButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (getText(sellNoField, sellNameField, sellPassField, sellEmailField, sellAddField, sellTelField)) {
                    return;
                }
                try {
                    if(sellerDao.cheakSeller(sellNoField.getText(),sellNameField.getText(), sellPassField.getText(), sellEmailField.getText() + comboBox.getSelectedItem(), sellAddField.getText(), sellTelField.getText())=="账号已存在"){
                            JOptionPane.showMessageDialog(jf, "您已经有账号了请直接登录");
                    }
                    else if(sellerDao.cheakSeller(sellNoField.getText(),sellNameField.getText(), sellPassField.getText(), sellEmailField.getText() + comboBox.getSelectedItem(), sellAddField.getText(), sellTelField.getText())=="账号已被注册"){
                        JOptionPane.showMessageDialog(jf, "该账号已被注册。");
                    }
                    else {
                        JOptionPane.showMessageDialog(jf, "注册成功请登录");
                    }

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }


        });

    }

    private boolean getText(JTextField buyNoField, JTextField buyNameField, JTextField buyPassField, JTextField buyEmailField, JTextField buyAddField, JTextField buyTelField) {
        if (buyNoField.getText().length() == 0) {
            JOptionPane.showMessageDialog(jf, "用户号不能为空");
            return true;
        }
        if (buyNameField.getText().length() == 0) {
            JOptionPane.showMessageDialog(jf, "姓名不能为空");
            return true;
        }
        if (buyPassField.getText().length() < 6) {
            JOptionPane.showMessageDialog(jf, "密码长度最少为6位字符串");
            return true;
        }
        if (buyEmailField.getText().length() == 0) {
            JOptionPane.showMessageDialog(jf, "电子邮箱不能为空");
            return true;
        }
        if (buyAddField.getText().length() == 0) {
            JOptionPane.showMessageDialog(jf, "地址不能为空");
            return true;
        }
        if (buyTelField.getText().length() == 0) {
            JOptionPane.showMessageDialog(jf, "电话不能为空");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        RegFrame rf = new RegFrame();
        rf.Init();

    }
}
