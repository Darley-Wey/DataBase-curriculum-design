package com.system.view.seller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SellerFrm extends JFrame {

    private final JDesktopPane desktopPane;
    private static String usename;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SellerFrm frame = new SellerFrm(usename);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SellerFrm(String usename) {
        SellerFrm.usename = usename;
        setType(Type.POPUP);
        setTitle("卖家系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 700);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton button = new JButton("发布房源信息");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HouseInfoInterFrm houseinfoInterFrm = new HouseInfoInterFrm(usename);

                desktopPane.add(houseinfoInterFrm);
                houseinfoInterFrm.setVisible(true);
            }
        });
        button.setBounds(0, 0, 141, 40);
        contentPane.add(button);

        JButton btnNewButton = new JButton("查询房源信息");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SearchHouseInterFrm searchInterFrm = new SearchHouseInterFrm(usename);

                desktopPane.add(searchInterFrm);
                searchInterFrm.setVisible(true);
            }
        });
        btnNewButton.setBounds(0, 40, 141, 40);
        contentPane.add(btnNewButton);

        JButton button_1 = new JButton("查询个人信息");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchSellerInterFrm searchSellerInterFrm = new SearchSellerInterFrm(usename);

                desktopPane.add(searchSellerInterFrm);
                searchSellerInterFrm.setVisible(true);
            }
        });
        button_1.setBounds(0, 80, 141, 40);
        contentPane.add(button_1);

        JButton btnNewButton_1 = new JButton("修改个人信息");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlterSellerInterFrm alterInterFrm = new AlterSellerInterFrm(usename);

                desktopPane.add(alterInterFrm);
                alterInterFrm.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(0, 120, 141, 40);
        contentPane.add(btnNewButton_1);

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(141, 0, 1340, 660);
        contentPane.add(desktopPane);

        JButton btnSeeRent = new JButton("查询求租信息");
        btnSeeRent.addActionListener(e -> {
            SearchRentInterFrm searchRentFrm = new SearchRentInterFrm();
            //searchInterFrm.setVisible(true);
            desktopPane.add(searchRentFrm);
            searchRentFrm.setVisible(true);
        });

        btnSeeRent.setBounds(0, 160, 141, 40);
        contentPane.add(btnSeeRent);
        
        JButton btnSeeOrder = new JButton("查看我的订单");
        btnSeeOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new SellerSeeOrderFrm(usename).setVisible(true);
            
        	}
        });
        btnSeeOrder.setBounds(0, 199, 141, 40);
        contentPane.add(btnSeeOrder);
        
        JButton btnSeeContract = new JButton("查看我的合同");
        btnSeeContract.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new SellerSeeContractFrm(usename).setVisible(true);
        	}
        });
        btnSeeContract.setBounds(0, 238, 141, 40);
        contentPane.add(btnSeeContract);
    }
}
