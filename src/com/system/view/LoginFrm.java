package com.system.view;

import com.system.dao.AdminDao;
import com.system.dao.BuyerDao;
import com.system.dao.SellerDao;
import com.system.view.admin.AdminIndex;
import com.system.view.buyer.BuyerMain;
import com.system.view.seller.SellerFrm;
import jdbc.Jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class LoginFrm extends JFrame {
    JFrame picframe = new JFrame("房屋中介系统");
    Jdbc jdbc=new Jdbc();
    AdminDao adminDao=new AdminDao();
    BuyerDao buyerDao=new BuyerDao();
    SellerDao sellerDao=new SellerDao();

    private static String IDStr;
    private final JPanel contentPane;
    private final JTextField IDField = new JTextField();
    private final JTextField textPassword = new JTextField();
    private final JPasswordField passField = new JPasswordField();
    private final JButton regButton = new JButton("注册");
    private final JButton loginButton = new JButton("登录");
    private final JCheckBox checkBox = new JCheckBox("显示密码");
    private final String IDHintText = new String("请输入账号");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new LoginFrm().Init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrm() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setFocusable(true);

        JLabel idLabel = new JLabel("账    号:");
        idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        idLabel.setBounds(135, 104, 72, 32);
        contentPane.add(idLabel);


        IDField.setBounds(221, 104, 171, 32);
        contentPane.add(IDField);
        IDField.setColumns(10);
        IDField.setText(IDHintText);
        IDField.setForeground(Color.GRAY);

        IDField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                // TODO Auto-generated method stub
                //获取焦点时，清空提示内容

                String temp = IDField.getText();
                if (temp.equals(IDHintText)) {
                    IDField.setText("");
                    IDField.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent arg0) {
                // TODO Auto-generated method stub
                //失去焦点时，没有输入内容，显示提示内容

                String temp = IDField.getText();
                if (temp.equals("")) {
                    IDField.setForeground(Color.GRAY);
                    IDField.setText(IDHintText);
                }
            }

        });


        JLabel passLabel = new JLabel("密    码:");
        passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passLabel.setBounds(135, 165, 72, 27);
        contentPane.add(passLabel);

        textPassword.setBounds(221, 164, 171, 32);
        String passHintText = new String("请输入六位以上字符");
        textPassword.setText(passHintText);
        textPassword.setForeground(Color.GRAY);
        contentPane.add(textPassword);
        textPassword.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                //改变输入框的样式
                passField.setForeground(Color.black);
                //为密码框添加内部类鼠标监听（鼠标经过事件）  当转换为密码框的时候，自动获取到焦点
                passField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        passField.requestFocus();
                    }
                });
                //移除密码输入框，添加文本输入框，面板重画
                contentPane.remove(textPassword);
                contentPane.add(passField);
                passField.setBounds(221, 164, 171, 32);
                passField.setForeground(Color.BLACK);
                contentPane.updateUI();
                contentPane.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub

            }

        });


        checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {

            }

            @Override
            public void focusLost(FocusEvent arg0) {
                // TODO Auto-generated method stub
                //失去焦点时，没有输入内容，显示提示内容

                if (new String(passField.getPassword()).length() == 0) {
                    contentPane.remove(passField);
                    contentPane.add(textPassword);
                    textPassword.setBounds(221, 164, 171, 32);
                    textPassword.setForeground(Color.GRAY);
                    contentPane.updateUI();
                    contentPane.repaint();
                }

            }

        });


        checkBox.setBounds(420, 169, 133, 27);
        checkBox.setOpaque(false);
        contentPane.add(checkBox);


        JLabel IDLabel = new JLabel("身    份:");
        IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        IDLabel.setBounds(135, 229, 72, 18);
        contentPane.add(IDLabel);

        JRadioButton adminRadioButton = new JRadioButton("职工");
        adminRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        adminRadioButton.setBounds(221, 221, 105, 35);
        adminRadioButton.setOpaque(false);

        contentPane.add(adminRadioButton);
        adminRadioButton.addActionListener(e -> {
            if (adminRadioButton.isSelected()) {
                IDStr = adminRadioButton.getText();
            }
        });

        JRadioButton buyerRadioButton = new JRadioButton("买家");
        buyerRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        buyerRadioButton.setBounds(334, 228, 77, 24);
        buyerRadioButton.setOpaque(false);
        contentPane.add(buyerRadioButton);

        buyerRadioButton.addActionListener(e -> {

            if (buyerRadioButton.isSelected()) {
                IDStr = buyerRadioButton.getText();
            }
        });

        JRadioButton sellerRadioButton = new JRadioButton("卖家");
        sellerRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        sellerRadioButton.setBounds(440, 223, 77, 35);
        sellerRadioButton.setOpaque(false);
        contentPane.add(sellerRadioButton);
        sellerRadioButton.addActionListener(e -> {
            if (sellerRadioButton.isSelected()) {
                IDStr = sellerRadioButton.getText();
            }
        });
        ButtonGroup IDGroup = new ButtonGroup();
        IDGroup.add(buyerRadioButton);
        IDGroup.add(sellerRadioButton);
        IDGroup.add(adminRadioButton);


        loginButton.setBounds(196, 291, 91, 35);
        contentPane.add(loginButton);

        regButton.setBounds(347, 291, 91, 35);
        contentPane.add(regButton);


    }


    public void Init() throws ClassNotFoundException {

        // 为登录按钮添加事件监听器
        loginButton.addActionListener(e -> {
            String str = new String(passField.getPassword());
            //检查数据库连接
            if (!jdbc.checkSql()) {
                JOptionPane.showMessageDialog(picframe, "数据库连接失败，无法登录");
                return;
            }
            // 登录成功则显示“登录成功”
            if (Objects.equals(IDStr, "职工")) {
                System.out.println(IDField.getText() + "\t" + str + "\t" + adminDao.validateAdmin(IDField.getText(), str));
                if (adminDao.validateAdmin(IDField.getText(), str)) {
                    String Adminname = IDField.getText();
                    JOptionPane.showMessageDialog(picframe, "登录成功");
                    new AdminIndex(Adminname).setVisible(true);//打开职工系统

                }
                // 否则显示“登录失败”
                else {
                    JOptionPane.showMessageDialog(picframe, "登录失败");
                }
            } else if (Objects.equals(IDStr, "买家")) {
                System.out.println(IDField.getText() + "\t" + str + "\t" + buyerDao.validateBuyer(IDField.getText(), str));
                if (buyerDao.validateBuyer(IDField.getText(), str)) {
                    String Buyername = IDField.getText();
                    JOptionPane.showMessageDialog(picframe, "登录成功");
                    new BuyerMain(Buyername).setVisible(true);//打开买家系统

                }
                // 否则显示“登录失败”
                else {
                    JOptionPane.showMessageDialog(picframe, "登录失败");
                }
            } else if (Objects.equals(IDStr, "卖家")) {
                System.out.println(IDField.getText() + "\t" + str + "\t" + sellerDao.validateSeller(IDField.getText(), str));
                if (sellerDao.validateSeller(IDField.getText(), str)) {
                    String Sellername = IDField.getText();
                    JOptionPane.showMessageDialog(picframe, "登录成功");
                    new SellerFrm(Sellername).setVisible(true);//打开卖家系统

                }

                // 否则显示“登录失败”
                else {
                    JOptionPane.showMessageDialog(picframe, "登录失败，请检查输入");
                }

            } else if (IDStr == null) {
                JOptionPane.showMessageDialog(picframe, "请选择登录身份");
            }

        });

        // 为注册按钮添加事件监听器
        regButton.addActionListener(arg0 -> {
            // TODO Auto-generated method stub
            if (!jdbc.checkSql()) {
                JOptionPane.showMessageDialog(picframe, "数据库连接失败，无法注册");
                return;
            }
            picframe.dispose();
            try {
                new RegFrame().Init();//进入注册界面
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {//被选中密码显示
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('*');
            }
        });

        ImageIcon icon = new ImageIcon("img\\house4.png");
        JLabel piclabel = new JLabel(icon);
        piclabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        picframe.getLayeredPane().add(piclabel, new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) picframe.getContentPane();
        jp.setOpaque(false);
        picframe.getContentPane().add(contentPane);
        picframe.setSize(icon.getIconWidth(), icon.getIconHeight());
        contentPane.setOpaque(false);
        picframe.setVisible(true);
        picframe.setLocationRelativeTo(null);
    }

}


