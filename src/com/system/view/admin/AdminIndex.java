package com.system.view.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.system.dao.HouseInfoDao;
import com.system.dao.OrderDao;
import com.system.model.SQLHouse;
import com.system.util.StringUtil;
import jdbc.Jdbc;

import java.awt.Font;

public class AdminIndex extends JFrame {
    /**
     * 用于创建界面
     */
    private static final long serialVersionUID = 1L;
    private String facticity;
    private String status;
    JPanel panelLeft, panelRight;
    JPanel jpR1, jpR2, jpR3, jpR4, jpOrder;
    CardLayout c1 = new CardLayout();
    private final String user;
    private String name;
    private final JTextField Housetext;
    private final JTable tableHouse;
    Jdbc jdbcBean = new Jdbc();
    HouseInfoDao houseInfoDao = new HouseInfoDao();
    OrderDao orderDao = new OrderDao();
    private final JTextField Hnotext;
    private final JTable tableOrder;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminIndex frame = new AdminIndex(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminIndex(String userAd) {
        this.user = userAd;

        setTitle("职工系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 1168, 632);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //将整个panel分为两个界面
        JSplitPane splitPane = new JSplitPane();
        splitPane.setBounds(0, 0, 1150, 572);
        contentPane.add(splitPane);
        //左边的panel为122像素
        splitPane.setDividerLocation(130);
        splitPane.setDividerSize(2);
        panelLeft = new JPanel();
        splitPane.setLeftComponent(panelLeft);
        panelLeft.setLayout(null);


        JButton buttonAdmin = new JButton("职工信息");
        buttonAdmin.setIcon(new ImageIcon("img\\20190113235608.png"));
        buttonAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(panelRight, "1");
            }
        });
        buttonAdmin.setBounds(0, 0, 129, 60);
        panelLeft.add(buttonAdmin);

        JButton buttonIncome = new JButton("营业统计");
        buttonIncome.setIcon(new ImageIcon("img\\edit.png"));
        buttonIncome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(panelRight, "2");
            }
        });
        //左边的列表设置
        buttonIncome.setBounds(0, 60, 129, 60);
        panelLeft.add(buttonIncome);

        JButton buttonUser = new JButton("用户管理");
        buttonUser.setIcon(new ImageIcon("img\\me.png"));
        buttonUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(panelRight, "3");
            }
        });
        buttonUser.setBounds(0, 120, 129, 60);
        panelLeft.add(buttonUser);

        JButton buttonHouse = new JButton("房源管理");
        buttonHouse.setIcon(new ImageIcon("img\\bookManager.png"));
        buttonHouse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(panelRight, "4");
            }
        });
        buttonHouse.setBounds(0, 180, 129, 60);
        panelLeft.add(buttonHouse);

        JButton buttonDeal = new JButton("交易管理");
        buttonDeal.addActionListener(arg0 -> {
        });
        buttonDeal.setIcon(new ImageIcon("img\\login.png"));
        buttonDeal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.show(panelRight, "5");
            }
        });
        buttonDeal.setBounds(0, 240, 129, 60);
        panelLeft.add(buttonDeal);


        //退出系统
        JButton btnExit = new JButton("退出");
        btnExit.setIcon(new ImageIcon("img\\exit.png"));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确定退出系统？", "提示", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(0, 300, 129, 60);
        panelLeft.add(btnExit);

        //右边的panel；
        panelRight = new JPanel(c1);
        splitPane.setRightComponent(panelRight);

        jpR1 = new JPanel();
        jpR1.setBackground(Color.WHITE);
        jpR2 = new JPanel();
        jpR2.setBackground(Color.WHITE);
        jpR3 = new JPanel();
        jpR3.setBackground(Color.WHITE);
        jpR4 = new JPanel();
        jpR4.setBackground(Color.WHITE);
        jpOrder = new JPanel();
        jpOrder.setBackground(Color.WHITE);

        panelRight.add("1", jpR1);
        jpR1.setLayout(null);

        JButton btnNewButton = new JButton("查看修改个人信息");
        btnNewButton.setBounds(706, 24, 159, 34);
        jpR1.add(btnNewButton);

        JDesktopPane adminDesk = new JDesktopPane();
        adminDesk.setBounds(0, 70, 1000, 500);
        jpR1.add(adminDesk);

        //查询登录职工的姓名
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            try (Statement ignored = con.createStatement()) {
                try (PreparedStatement ps = con.prepareStatement("SELECT A_name FROM Admin WHERE A_id =?")) {
                    ps.setObject(1, user); // 注意：索引从1开始
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            name = rs.getString("A_name");
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(con);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }


        JLabel lblWelcome = new JLabel(name + "欢迎你！");//欢迎词
        lblWelcome.setFont(new Font("宋体", Font.PLAIN, 18));
        lblWelcome.setIcon(new ImageIcon("img\\userName.png"));
        lblWelcome.setBounds(514, 23, 165, 32);
        jpR1.add(lblWelcome);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminLook adminLook = new AdminLook(user);
                System.out.println(user);//用于预显示
                adminLook.setVisible(true);
                adminDesk.add(adminLook);
            }
        });

        panelRight.add("2", jpR2);
        jpR2.setLayout(null);
        JButton buttonTurnover = new JButton("营业额统计");
        buttonTurnover.setIcon(new ImageIcon("img\\bookTypeManager.png"));
        buttonTurnover.setBounds(0, 0, 500, 70);
        jpR2.add(buttonTurnover);
        JButton buttonAdminIncome = new JButton("职工业绩统计");
        buttonAdminIncome.setIcon(new ImageIcon("img\\modify.png"));
        buttonAdminIncome.setBounds(500, 0, 500, 70);
        jpR2.add(buttonAdminIncome);

        JDesktopPane annoDesk = new JDesktopPane();
        annoDesk.setBounds(0, 70, 1000, 500);
        jpR2.add(annoDesk);

        JLabel labelTime = new JLabel("发布时间");
        labelTime.setBounds(280, 27, 72, 18);
        buttonAdminIncome.addActionListener(e -> {
            IncomeLook intFrame = new IncomeLook();

            annoDesk.add(intFrame);
            intFrame.setVisible(true);
        });
        buttonTurnover.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TurnoverLook intFrameTurnoverLook = new TurnoverLook();
                annoDesk.add(intFrameTurnoverLook);
                intFrameTurnoverLook.setVisible(true);
            }
        });

        panelRight.add("3", jpR3);
        jpR3.setLayout(null);

        JButton btnBuyer = new JButton("买家信息");
        btnBuyer.setBounds(0, 0, 500, 70);
        jpR3.add(btnBuyer);

        JButton btnSeller = new JButton("卖家信息");
        btnSeller.setBounds(500, 0, 500, 70);
        jpR3.add(btnSeller);

        JDesktopPane userdesk = new JDesktopPane();
        userdesk.setBounds(0, 70, 1000, 500);
        jpR3.add(userdesk);

        //实现Buyer和Seller的内置页面
        btnBuyer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BuyerInfo buyerInfo = new BuyerInfo();

                userdesk.add(buyerInfo);
                buyerInfo.setVisible(true);
            }
        });
        btnSeller.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SellerInfo sellerinfo = new SellerInfo();

                userdesk.add(sellerinfo);
                sellerinfo.setVisible(true);
            }
        });


        panelRight.add("4", jpR4);
        jpR4.setLayout(null);

        Housetext = new JTextField();
        Housetext.setBounds(138, 30, 135, 30);
        jpR4.add(Housetext);
        Housetext.setColumns(10);
        //条件查询
        JButton housecheck = new JButton("查询");
        housecheck.setBounds(309, 30, 84, 30);
        housecheck.setIcon(new ImageIcon("img\\search.png"));
        housecheck.addActionListener(e -> {
            String hNo = Housetext.getText();
            SQLHouse sqlHouse = new SQLHouse();
            sqlHouse.setH_no(hNo);
            fillTableHouse(sqlHouse);
        });
        jpR4.add(housecheck);

        JLabel lblHouseNo = new JLabel("房源编号");
        lblHouseNo.setBounds(50, 30, 72, 30);
        jpR4.add(lblHouseNo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 70, 1000, 400);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpR4.add(scrollPane);

        tableHouse = new JTable();
        //获取表格的点击事件
        tableHouse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tableHouse.getSelectedRow();
                Hnotext.setText((String) tableHouse.getValueAt(row, 0));
                //int row=tableHouse.getSelectedRow();
                facticity = (String) tableHouse.getValueAt(row, 11);
                status = (String) tableHouse.getValueAt(row, 12);

            }
        });
        tableHouse.setRowSelectionAllowed(false);
        tableHouse.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "房源编号", "卖家号", "所属地址", "建筑单位", "朝向", "户型", "面积", "楼层", "单元号", "车库面积", "装修状况", "真实性", "交易状态", "出租价格(元/月)", "出售价格(万元)"
                }
        ));
        tableHouse.getColumnModel().getColumn(2).setPreferredWidth(140);
        tableHouse.getColumnModel().getColumn(4).setPreferredWidth(40);
        tableHouse.getColumnModel().getColumn(6).setPreferredWidth(40);
        tableHouse.getColumnModel().getColumn(7).setPreferredWidth(40);
        tableHouse.getColumnModel().getColumn(8).setPreferredWidth(60);
        tableHouse.getColumnModel().getColumn(11).setPreferredWidth(60);
        tableHouse.getColumnModel().getColumn(13).setPreferredWidth(125);
        tableHouse.getColumnModel().getColumn(14).setPreferredWidth(120);
        scrollPane.setViewportView(tableHouse);

        JButton deleHouseButton = new JButton("删除房源");
        deleHouseButton.setBounds(862, 500, 113, 30);
        deleHouseButton.setIcon(new ImageIcon("img\\delete.png"));
        deleHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no = Hnotext.getText();
                if (StringUtil.isEmpty(no)) {
                    JOptionPane.showMessageDialog(null, "请选择要删除的行");
                    return;
                }
                if (status.equals("交易中")) {
                    JOptionPane.showMessageDialog(null, "该房源交易中，不可删除");
                    return;
                }
                if (status.equals("已交易")) {
                    JOptionPane.showMessageDialog(null, "该房源已交易，不可删除");
                    return;
                }
                Connection con = null;
                try {
                    con = jdbcBean.getConnection();
                    int num = houseInfoDao.houseDelete(con, no);
                    if (num == 1) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    try {
                        jdbcBean.closeCt(con);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
        jpR4.add(deleHouseButton);

        JLabel labelHouseNo = new JLabel("房源编号");
        labelHouseNo.setBounds(492, 500, 72, 30);
        jpR4.add(labelHouseNo);

        Hnotext = new JTextField();
        Hnotext.setBounds(578, 500, 127, 30);
        jpR4.add(Hnotext);
        Hnotext.setColumns(10);

        JButton btnNewButton5 = new JButton("审核房源");
        btnNewButton5.setIcon(new ImageIcon("img\\modify.png"));
        btnNewButton5.addActionListener(arg0 -> {
            String no = Hnotext.getText();
            if (StringUtil.isEmpty(no)) {
                JOptionPane.showMessageDialog(null, "请选择要审核的行");
                return;
            }
            if (facticity.equals("已审核")) {
                JOptionPane.showMessageDialog(null, "该房源已审核");
                return;

            }
            Connection con1 = null;

            try {
                int result = JOptionPane.showConfirmDialog(null, "确定该房源真实？");
                if (result == JOptionPane.YES_OPTION) {
                    con1 = jdbcBean.getConnection();
                    int num = houseInfoDao.houseVerify(con1, no);
                    if (num == 1) {
                        JOptionPane.showMessageDialog(null, "审核成功");
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally {
                try {
                    jdbcBean.closeCt(con1);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

        });
        btnNewButton5.setBounds(719, 500, 113, 30);
        jpR4.add(btnNewButton5);
        panelRight.add("5", jpOrder);
        jpOrder.setLayout(null);

        JScrollPane scrollPaneOrder = new JScrollPane();
        scrollPaneOrder.setBounds(50, 70, 900, 470);
        jpOrder.add(scrollPaneOrder);

        tableOrder = new JTable();
        tableOrder.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "订单号", "买家用户号", "卖家用户号", "房源编号", "出租价格(元/月)", "出售价格(万元)", "订单状态"
                }
        ));

        scrollPaneOrder.setViewportView(tableOrder);

        JButton buttonCheckOrder = new JButton("查看全部订单");
        buttonCheckOrder.addActionListener(e -> fillTableOrder());
        buttonCheckOrder.setIcon(new ImageIcon("img\\search.png"));
        buttonCheckOrder.setBounds(120, 23, 130, 39);
        jpOrder.add(buttonCheckOrder);

        JButton btnNewContract = new JButton("签订合同");
        btnNewContract.addActionListener(e -> new Contract(user, name).setVisible(true));
        btnNewContract.setBounds(597, 23, 120, 39);
        jpOrder.add(btnNewContract);
        
        JButton btnSeeRentOrder = new JButton("查看租房订单");
        btnSeeRentOrder.addActionListener(e -> fillRentOrder());
        btnSeeRentOrder.setBounds(280, 23, 130, 39);
        jpOrder.add(btnSeeRentOrder);
        
        JButton btnSeeBuyOrder = new JButton("查看买房订单");
        btnSeeBuyOrder.addActionListener(e -> fillBuyOrder());
        btnSeeBuyOrder.setBounds(440, 23, 130, 39);
        jpOrder.add(btnSeeBuyOrder);
        fillTableHouse(new SQLHouse());


    }

    //查看全部房源,填充表格
    private void fillTableHouse(SQLHouse sqlHouse) {
        DefaultTableModel dtm4 = (DefaultTableModel) tableHouse.getModel();
        dtm4.setRowCount(0);
        Connection conn = null;
        try {
            conn = jdbcBean.getConnection();
            ResultSet rs = houseInfoDao.houseCheck(conn, sqlHouse);
            while (rs.next()) {
                Vector<Object> vt = new Vector<>();
                vt.add(rs.getObject("H_no"));
                vt.add(rs.getObject("S_no"));
                vt.add(rs.getObject("reg_ad"));
                vt.add(rs.getObject("Item_cop"));
                vt.add(rs.getObject("dir"));
                vt.add(rs.getObject("Stru_na"));
                vt.add(rs.getObject("area"));
                vt.add(rs.getObject("floor"));
                vt.add(rs.getObject("unit_no"));
                vt.add(rs.getObject("cararea"));
                vt.add(rs.getObject("fitment"));
                vt.add(rs.getObject("facticity"));
                vt.add(rs.getObject("status"));
                vt.add(rs.getObject("price"));
                vt.add(rs.getObject("money"));
                //vt.add(rs.getObject("接受订单"));
                dtm4.addRow(vt);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(conn);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }

    }

    //查看全部订单,填充表格
    private void fillTableOrder() {
        DefaultTableModel dtm5 = (DefaultTableModel) tableOrder.getModel();
        dtm5.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            ResultSet rst = orderDao.DealList(con);
            while (rst.next()) {
                Vector<Object> vto = new Vector<>();
                vto.add(rst.getObject("order_no"));
                vto.add(rst.getObject("B_no"));
                vto.add(rst.getObject("S_no"));
                vto.add(rst.getObject("H_no"));
                vto.add(rst.getObject("price"));
                vto.add(rst.getObject("money"));
                vto.add(rst.getObject("order_stu"));
                dtm5.addRow(vto);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(con);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }

    }

    //查看租房订单,填充表格
    private void fillRentOrder() {
        DefaultTableModel dtm5 = (DefaultTableModel) tableOrder.getModel();
        dtm5.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            ResultSet rst = orderDao.RentList(con);
            while (rst.next()) {
                Vector<Object> vto = new Vector<>();
                vto.add(rst.getObject("order_no"));
                vto.add(rst.getObject("B_no"));
                vto.add(rst.getObject("S_no"));
                vto.add(rst.getObject("H_no"));
                vto.add(rst.getObject("price"));
                vto.add(rst.getObject("money"));
                vto.add(rst.getObject("order_stu"));
                dtm5.addRow(vto);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(con);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }

    }

    //查看买房订单,填充表格
    private void fillBuyOrder() {
        DefaultTableModel dtm5 = (DefaultTableModel) tableOrder.getModel();
        dtm5.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcBean.getConnection();
            ResultSet rst = orderDao.BuyList(con);
            while (rst.next()) {
                Vector<Object> vto = new Vector<>();
                vto.add(rst.getObject("order_no"));
                vto.add(rst.getObject("B_no"));
                vto.add(rst.getObject("S_no"));
                vto.add(rst.getObject("H_no"));
                vto.add(rst.getObject("price"));
                vto.add(rst.getObject("money"));
                vto.add(rst.getObject("order_stu"));
                dtm5.addRow(vto);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(con);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }

    }
}
