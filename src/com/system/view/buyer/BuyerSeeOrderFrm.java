package com.system.view.buyer;

import com.system.dao.OrderDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;



public class BuyerSeeOrderFrm extends JFrame {

    Jdbc jdbc=new Jdbc();
    OrderDao orderDao=new OrderDao();
    private final String usename;
    private final JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BuyerSeeOrderFrm frame = new BuyerSeeOrderFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public BuyerSeeOrderFrm(String usename) {
        setTitle("查看订单信息");
        this.usename = usename;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setBounds(410, 155, 605, 490);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "订单号", "买家号", "卖家号", "房源编号", "订单状态"
                }
        ));
        scrollPane.setViewportView(table);
        this.fillTable();
    }

    public void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        try (Connection con = jdbc.getConnection()) {

            ResultSet rs = orderDao.buyerSeeOrder(con, usename);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("order_no"));
                v.add(rs.getObject("B_no"));
                v.add(rs.getObject("S_no"));
                v.add(rs.getObject("H_no"));
                v.add(rs.getObject("order_stu"));
			/*v.add(rs.getObject("S_name"));
			v.add(rs.getObject("S_tel"));
			v.add(rs.getObject("B_name"));
			v.add(rs.getObject("B_tel"));*/
                dtm.addRow(v);
            }
            Vector<Object> v = new Vector<>();
            //添加空行，美化
            for (int i = 0; i < 30; i++) {
                dtm.addRow(v);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO: handle exception
    }


}
