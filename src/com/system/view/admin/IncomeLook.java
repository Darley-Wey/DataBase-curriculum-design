package com.system.view.admin;

import com.system.dao.ContractDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class IncomeLook extends JInternalFrame {


    Jdbc jdbcutil = new Jdbc();
    ContractDao sqlAnnodao = new ContractDao();
    private final JTable table;
    List<String> list = new ArrayList<>();
    String[] arr = new String[list.size()];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                IncomeLook frame = new IncomeLook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public IncomeLook() {
        setTitle("职工业绩统计");
        setIconifiable(true);//缩小按钮
        setClosable(true);
        setBounds(0, 0, 990, 490);
        getContentPane().setLayout(null);

        JButton AnnoButton = new JButton("查询全部");
        AnnoButton.addActionListener(e -> fillTable());
        AnnoButton.setBounds(752, 287, 113, 31);
        getContentPane().add(AnnoButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(69, 13, 586, 428);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "职工号", "职工姓名", "业绩（元）"
                }
        ));
        /*
         * DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
         * // tcr.setHorizontalAlignment(JLabel.CENTER);
         * tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
         * table.setDefaultRenderer(Object.class, tcr);
         */
        scrollPane.setViewportView(table);

        //检索职工号列表加入下拉菜单
        try {

            Connection conn = jdbcutil.getConnection();//建立connection
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式
            String sql = "SELECT A_id FROM Admin";//查询语句
            ResultSet rs = stmt.executeQuery(sql);//得到结果集
            conn.commit();//事务提交
            conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            while (rs.next()) {//如果有数据，取第一列添加如list
                list.add(rs.getString(1));
            }
            if (list != null && list.size() > 0) {//如果list中存入了数据，转化为数组
                arr = new String[list.size()];//创建一个和list长度一样的数组
                for (int i = 0; i < list.size(); i++) {
                    arr[i] = list.get(i);//数组赋值了。
                }
                //输出数组
                for (String s : arr) {
                    System.out.println(s);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        class Model extends DefaultComboBoxModel<String> {

            private static final long serialVersionUID = 1L;

            Model(String[] s) {

                for (String value : s) {
                    addElement(value);
                }

            }

        }
        ComboBoxModel<String> model = new Model(arr);
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setBounds(752, 105, 113, 29);
        getContentPane().add(comboBox);

        JButton btnNewButton = new JButton("查询");
        btnNewButton.addActionListener(arg0 -> {
            String A_id = (String) comboBox.getSelectedItem();
            fillTable1(A_id);
        });
        btnNewButton.setBounds(752, 175, 113, 31);
        getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("选择职工号");
        lblNewLabel.setBounds(765, 57, 90, 37);
        getContentPane().add(lblNewLabel);


    }

    private void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcutil.getConnection();
            ResultSet rs = sqlAnnodao.revenueList(con);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("A_id"));
                v.add(rs.getObject("A_name"));
                v.add(rs.getObject("fee"));
//			v.add(rs.getObject("content"));
//			v.add(rs.getObject("A_sum"));
//			v.add(rs.getObject("H_sum"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcutil.closeCt(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable1(String A_id) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = jdbcutil.getConnection();
            ResultSet rs = sqlAnnodao.revenueList1(con, A_id);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("A_id"));
                v.add(rs.getObject("A_name"));
                v.add(rs.getObject("fee"));
//			v.add(rs.getObject("content"));
//			v.add(rs.getObject("A_sum"));
//			v.add(rs.getObject("H_sum"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcutil.closeCt(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}