package com.system.view.admin;

import com.system.dao.ContractDao;
import com.system.dao.HouseInfoDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.util.Objects;


public class Contract extends JFrame {

    private final JTextField textFieldtime;
    private final JTextField textFieldAdminNo;
    private final JTextField textFieldAdminName;
    private final JTextField textFieldFee;
    Jdbc jdbc = new Jdbc();
    ContractDao contractDao = new ContractDao();
    HouseInfoDao houseInfoDao = new HouseInfoDao();
    Connection con = jdbc.getConnection();

    private final JTextField textFieldOrderNo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Contract frame = new Contract(null, null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     * 填写出租出售合同
     */
    public Contract(String usename, String name) {
        setTitle("签订合同");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setBounds(410, 155, 605, 490);
        JPanel contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelSty = new JLabel("合同方式");
        labelSty.setBounds(90, 133, 70, 19);
        contentPane.add(labelSty);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(174, 125, 96, 29);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"", "出租", "出售"}));
        contentPane.add(comboBox);

        JLabel labelTime = new JLabel("签订时间");
        labelTime.setBounds(90, 170, 70, 26);
        contentPane.add(labelTime);
        textFieldtime = new JTextField();
        textFieldtime.setColumns(10);
        textFieldtime.setToolTipText("yyyy-mm-dd");
        textFieldtime.setBounds(174, 166, 96, 30);
        contentPane.add(textFieldtime);

        JLabel labelAno = new JLabel("职工号");
        labelAno.setBounds(311, 82, 54, 30);
        contentPane.add(labelAno);
        textFieldAdminNo = new JTextField();
        textFieldAdminNo.setEditable(false);
        textFieldAdminNo.setColumns(10);
        textFieldAdminNo.setBounds(379, 82, 96, 32);
        contentPane.add(textFieldAdminNo);
        textFieldAdminNo.setText(usename);

        JLabel labelAdminName = new JLabel("职工姓名");
        labelAdminName.setBounds(298, 133, 67, 19);
        contentPane.add(labelAdminName);
        textFieldAdminName = new JTextField();
        textFieldAdminName.setEditable(false);
        textFieldAdminName.setColumns(10);
        textFieldAdminName.setBounds(379, 125, 96, 29);
        contentPane.add(textFieldAdminName);
        textFieldAdminName.setText(name);

        JLabel labelFee = new JLabel("中介费");
        labelFee.setBounds(311, 170, 54, 26);
        contentPane.add(labelFee);
        textFieldFee = new JTextField();
        textFieldFee.setColumns(10);
        textFieldFee.setBounds(379, 166, 96, 30);
        contentPane.add(textFieldFee);

        JLabel labelOrderNo = new JLabel("订单号");
        labelOrderNo.setBounds(90, 82, 54, 30);
        contentPane.add(labelOrderNo);
        textFieldOrderNo = new JTextField();
        textFieldOrderNo.setBounds(174, 82, 96, 30);
        contentPane.add(textFieldOrderNo);
        textFieldOrderNo.setColumns(10);

        JButton button = new JButton("提交");
        button.addActionListener(e -> {
            String str1 = textFieldOrderNo.getText();//订单号
            String str2 = textFieldAdminNo.getText();//职工号
            String str3 = Objects.requireNonNull(comboBox.getSelectedItem()).toString();//合同方式
            String str4 = textFieldAdminName.getText();//职工姓名
            String str5 = textFieldtime.getText();//签订时间
            String str6 = textFieldFee.getText();//中介费
            if (str1.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入订单号");
                return;
            }
            if (str3.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入合同方式");
                return;
            }
            if (str5.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入签订时间");
                return;
            }
            if (str6.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入中介费");
                return;
            }

            try {
                if (str3.equals("出售")) {
                    int result = JOptionPane.showConfirmDialog(null, "确定签订出售合同并完成过户?", "提示", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        contractDao.contractAdd(con, str1, str2, str3, str4, str5, str6);//签订出租合同
                        houseInfoDao.changehouseinfo(con, str1);//改变房源中的房源状态
                        JOptionPane.showMessageDialog(null, "签订出售合同成功");
                    }
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "确定签订出租合同?", "提示", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        contractDao.contractAdd(con, str1, str2, str3, str4, str5, str6);//签订出售合同
                        houseInfoDao.changehouseinfo(con, str1);//改变房源中的房源状态
                        JOptionPane.showMessageDialog(null, "签订出租合同成功");
                    }
                }

                //清空输入框
                textFieldOrderNo.setText("");
                comboBox.setSelectedIndex(0);
                textFieldtime.setText("");
                textFieldAdminNo.setText(usename);
                textFieldAdminName.setText(name);
                textFieldFee.setText("");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        button.setBounds(186, 269, 107, 32);
        contentPane.add(button);


    }
}
