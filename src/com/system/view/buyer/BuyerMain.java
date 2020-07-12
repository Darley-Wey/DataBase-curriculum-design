package com.system.view.buyer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuyerMain extends JFrame {
    public static Object string;
    private static String usename;
    BuyerOwnInfoFrm buyerOwnInfo = new BuyerOwnInfoFrm(usename);

    public BuyerMain(String usename) {
        setTitle("买家系统");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        BuyerMain.usename = usename;
		/*int weight=738;
		int height=523;*/
        //加载图片
		/*ImageIcon icon=new ImageIcon("C:\\\\Users\\\\Administrator\\\\Pictures\\\\beijing.JPG");
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel la=new JLabel(icon);
		
		//设置label的大小
		la.setBounds(100,100,icon.getIconWidth(),icon.getIconHeight());

		
		//获取窗口的第二层，将label放入
		getLayeredPane().add(la,new Integer(Integer.MIN_VALUE));
			
		//获取frame的顶层容器,并设置为透明
		JPanel j=(JPanel)this.getContentPane();
		j.setOpaque(false);*/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 738, 523);
        this.setLocationRelativeTo(null);
        JTabbedPane jp = new JTabbedPane(JTabbedPane.LEFT);
        //jp.setOpaque(false);
        jp.setPreferredSize(new Dimension(200, 400));
        this.setVisible(true);
//设置选项卡在坐标 
        JPanel p2 = new JPanel();
        p2.setOpaque(false);
        p2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p2.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new BuyerOwnInfoFrm(usename).setVisible(true);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
        });


        JPanel p3 = new JPanel();
        p3.setOpaque(false);
        p3.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new ModifyBuyerInfoFrm(usename).setVisible(true);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
        });

        JPanel p4 = new JPanel();      //创建多个容器
        p4.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new BuyerSeeContractFrm(usename).setVisible(true);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
        });
        p4.setOpaque(false);
		    /*
		      对查询房源页面操作
		     */

        JPanel p6 = new JPanel();
        p6.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int result = JOptionPane.showConfirmDialog(null, "确定退出系统？");
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
        });

        JPanel p1 = new JPanel();
        jp.addTab("  查询房源信息", new ImageIcon("img\\sousuo.png"), p1, null);
        p1.setBackground(SystemColor.controlHighlight);
        p1.setForeground(Color.WHITE);
        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        JLabel label = new JLabel("面积");
        label.setBounds(79, 97, 30, 23);
        label.setOpaque(false);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(123, 93, 118, 30);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"", "50平以下", "50-150平", "150-200平", "200平以上"}));
        comboBox.setOpaque(false);
        comboBox.addItemListener(e -> {
        });

        JLabel label_1 = new JLabel("朝向");
        label_1.setBounds(79, 151, 30, 18);
        label_1.setOpaque(false);
        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(123, 145, 118, 31);
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[]{"", "朝阳", "朝阴"}));
        comboBox_1.setOpaque(false);
        comboBox_1.addItemListener(e -> {
        });

        JLabel label_2 = new JLabel("户型");
        label_2.setBounds(299, 99, 30, 18);
        label_2.setOpaque(false);
        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setBounds(345, 91, 118, 34);
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[]{"", "单居室", "两室一厅", "三室一厅", "三室两厅", "四室两厅"}));
        comboBox_2.setOpaque(false);
        comboBox_2.addItemListener(e -> {
        });

        JLabel label_3 = new JLabel("楼层");
        label_3.setBounds(299, 151, 30, 18);
        label_3.setOpaque(false);
        JComboBox<String> comboBox_3 = new JComboBox<>();
        comboBox_3.setBounds(345, 145, 118, 31);
        comboBox_3.setModel(new DefaultComboBoxModel<>(new String[]{"", "1-6层", "7-16层", "17层以上"}));
        comboBox_3.setOpaque(false);
        comboBox_3.addItemListener(e -> {
        });
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(260, 190, 1, 1);

        JLabel label_4 = new JLabel("出售价格");
        label_4.setBounds(52, 203, 73, 28);
        label_4.setOpaque(false);
        JComboBox<String> comboBox_4 = new JComboBox<>();
        comboBox_4.setBounds(123, 201, 118, 32);
        comboBox_4.setModel(new DefaultComboBoxModel<>(new String[]{"", "100万以下", "100万-200万", "200万以上"}));
        comboBox_4.setOpaque(false);
        JLabel label_5 = new JLabel("装修状况");
        label_5.setBounds(272, 206, 69, 23);
        label_5.setOpaque(false);
        JComboBox<String> comboBox_5 = new JComboBox<>();
        comboBox_5.setBounds(345, 201, 118, 32);
        comboBox_5.setModel(new DefaultComboBoxModel<>(new String[]{"", "简装", "毛坯", "精装"}));
        comboBox_5.setOpaque(false);
        JButton button = new JButton("分类查询");
        button.setBounds(202, 268, 127, 41);
        button.setOpaque(false);
        button.addActionListener(e -> {
            Object string = comboBox.getSelectedItem();
            Object string1 = comboBox_1.getSelectedItem();
            Object string2 = comboBox_2.getSelectedItem();
            Object string3 = comboBox_3.getSelectedItem();
            Object string4 = comboBox_4.getSelectedItem();
            Object string5 = comboBox_5.getSelectedItem();
            //System.out.println(string+""+string1+""+string2+""+string3+""+string4+""+string5);
            new ClassQueryFrm(usename, string, string1, string2, string3, string4, string5).setVisible(true);
        });

        JDesktopPane desktopPane = new JDesktopPane();
        contentPane.add(desktopPane);

        JButton btnNewButton = new JButton("发布求租");
        btnNewButton.setBounds(202, 388, 127, 42);
        btnNewButton.addActionListener(e -> {
            RentInterFrm rentInterFrm = new RentInterFrm(usename);
            rentInterFrm.setVisible(true);
            //desktopPane.add(rentInterFrm);
        });
        p1.setLayout(null);
        p1.add(btnNewButton);
        p1.add(label_1);
        p1.add(label);
        p1.add(label_4);
        p1.add(comboBox_1);
        p1.add(comboBox);
        p1.add(comboBox_4);
        p1.add(layeredPane);
        p1.add(label_5);
        p1.add(comboBox_5);
        p1.add(label_2);
        p1.add(comboBox_2);
        p1.add(label_3);
        p1.add(comboBox_3);
        p1.add(button);

        JButton btnNewButton_1 = new JButton("查询全部房源");
        btnNewButton_1.addActionListener(e -> new HouseFrm(usename).setVisible(true));
        btnNewButton_1.setBounds(201, 322, 128, 42);
        p1.add(btnNewButton_1);
        jp.add("查看个人信息", p2);
        buyerOwnInfo.fillTable();
        jp.setIconAt(1, new ImageIcon("img\\chaxun.png"));
        jp.add("修改个人信息", p3);
        jp.setIconAt(2, new ImageIcon("img\\20190113235608.png"));
        JPanel p7 = new JPanel();
        p7.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                new BuyerSeeOrderFrm(usename).setVisible(true);
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }
        });
        jp.add("查看订单信息", p7);
        jp.setIconAt(3, new ImageIcon("img\\chaxundingdan.png"));
        jp.add("查看合同信息", p4);
        jp.setIconAt(4, new ImageIcon("img\\chaxunjindu.png"));
        jp.add("退出系统", p6);
        jp.setIconAt(5, new ImageIcon("img\\tuichu.png"));
        // frame.getContentPane().add(jp);
        //this .add(p1);
        //f.add(p1);
			/*frame.setSize(icon.getIconWidth(),icon.getIconHeight());
			frame.setVisible(true);*/
        jp.setOpaque(false);
        getContentPane().add(jp);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(desktopPane);
        contentPane.add(jp);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            try {
                BuyerMain frame = new BuyerMain(usename);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}