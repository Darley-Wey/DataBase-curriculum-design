package com.system.view.admin;

import com.system.dao.ContractDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Vector;

public class TurnoverLook extends JInternalFrame {
    private final JTable tableinfo;
    Jdbc jabcUtil = new Jdbc();
    ContractDao incomeDao = new ContractDao();
	/*
	  Launch the application.
	 */

    /**
     * Create the frame.
     */
    public TurnoverLook() {
        setTitle("营业额统计");
        setIconifiable(true);
        setClosable(true);
        setBounds(0, 0, 990, 490);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JLabel lblNewLabel = new JLabel("           业务类型   ");
        menuBar.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"", "出租", "出售"}));
        menuBar.add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("           查询到年   ");
        menuBar.add(lblNewLabel_1);

        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[]{"", "2019", "2020", "2021", "2022", "2023"}));
        menuBar.add(comboBox_1);

        JLabel lblNewLabel_4 = new JLabel("           查询到月   ");
        menuBar.add(lblNewLabel_4);
        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[]{"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        menuBar.add(comboBox_2);

        JLabel lblNewLabel_5 = new JLabel("           查询到日   ");
        menuBar.add(lblNewLabel_5);
        JComboBox<String> comboBox_3 = new JComboBox<>();
        comboBox_3.setModel(new DefaultComboBoxModel<>(new String[]{"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        menuBar.add(comboBox_3);

        JButton btnNewButton = new JButton("查询");
        btnNewButton.addActionListener(e -> {
            String str1 = Objects.requireNonNull(comboBox.getSelectedItem()).toString();//业务类型
            String str2 = Objects.requireNonNull(comboBox_1.getSelectedItem()).toString();//业务时间
            String str3 = Objects.requireNonNull(comboBox_2.getSelectedItem()).toString();
            String str4 = Objects.requireNonNull(comboBox_3.getSelectedItem()).toString();
            if (str2.length() != 0) {
                if (str3.length() != 0) {
                    if (str4.length() != 0) {
                        str2 = str2 + "-" + str3 + "-" + str4;
                    } else {
                        str2 = str2 + "-" + str3;
                    }
                }
                fillTable(str1, str2);
            } else if (str1.length() == 0) {
                fillTable0();
            } else {
                fillTable1(str1);
            }
        });
        menuBar.add(btnNewButton);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 5, 940, 403);
        contentPane.add(scrollPane);

        tableinfo = new JTable();
        /*
         * DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
         * // tcr.setHorizontalAlignment(JLabel.CENTER);
         * tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
         * tableinfo.setDefaultRenderer(Object.class, tcr);
         */
        tableinfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        tableinfo.setRowSelectionAllowed(false);
        tableinfo.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "业务类型", "业务时间", "营业额"
                }
        ));
        tableinfo.getColumnModel().getColumn(0).setPreferredWidth(111);
        tableinfo.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableinfo.getColumnModel().getColumn(2).setPreferredWidth(103);
        scrollPane.setViewportView(tableinfo);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TurnoverLook frame = new TurnoverLook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void fillTable(String str1, String str2) {
        DefaultTableModel dtm = (DefaultTableModel) tableinfo.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = jabcUtil.getConnection();
            ResultSet rs = incomeDao.classTurnoverList(con, str1, str2);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("con_style"));
                v.add(str2);
                v.add(rs.getObject("fee"));
//				v.add(rs.getObject("content"));
//				v.add(rs.getObject("A_sum"));
//				v.add(rs.getObject("H_sum"));
//				v.add(rs.getObject("A_id"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jabcUtil.closeCt(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable0() {
        DefaultTableModel dtm = (DefaultTableModel) tableinfo.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = jabcUtil.getConnection();
            ResultSet rs = incomeDao.incomeList1(con);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("con_style"));
                v.add(rs.getObject("con_time"));
                v.add(rs.getObject("fee"));
//				v.add(rs.getObject("content"));
//				v.add(rs.getObject("A_sum"));
//				v.add(rs.getObject("H_sum"));
//				v.add(rs.getObject("A_id"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jabcUtil.closeCt(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable1(String str1) {
        DefaultTableModel dtm = (DefaultTableModel) tableinfo.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = jabcUtil.getConnection();
            ResultSet rs = incomeDao.incomeList(con, str1);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("con_style"));
                v.add(rs.getObject("con_time"));
                v.add(rs.getObject("fee"));
//			v.add(rs.getObject("content"));
//			v.add(rs.getObject("A_sum"));
//			v.add(rs.getObject("H_sum"));
//			v.add(rs.getObject("A_id"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jabcUtil.closeCt(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
