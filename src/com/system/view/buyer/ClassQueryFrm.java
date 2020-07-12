package com.system.view.buyer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.system.dao.HouseInfoDao;
import com.system.dao.OrderDao;
import jdbc.Jdbc;

/**
 * @author Darley
 */
public class ClassQueryFrm extends JFrame {

    private final JTable table;
    Jdbc jdbc = new Jdbc();
    OrderDao orderDao = new OrderDao();
    private final Object s, s1, s2, s3, s4, s5;
    Connection con = jdbc.getConnection();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClassQueryFrm frame = new ClassQueryFrm(null, null, null, null, null, null, null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     * 按分类条件展示全部已审核未交易的房源
     */
    public ClassQueryFrm(String usename, Object s, Object s1, Object s2, Object s3, Object s4, Object s5) {
        setTitle("分类查询房源信息");
        this.s = s;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setBounds(100, 100, 1400, 700);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        // contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);
                if (column == 14) {
                    try {
                        int result = JOptionPane.showConfirmDialog(null, "确定提交租房订单？");
                        if (result == JOptionPane.YES_OPTION) {
                            String str = (String) table.getValueAt(row, 0);
                            String str1 = (String) table.getValueAt(row, 1);
                            ResultSet rs = orderDao.chkRent(con, usename, str, str1);
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(null, "请不要重复提交相同订单");
                                return;
                            }
                            orderDao.selRent(con, usename, str, str1);
                            JOptionPane.showMessageDialog(null, "提交成功");
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }


                if (column == 15) {
                    try {
                        int result = JOptionPane.showConfirmDialog(null, "确定提交买房订单？");
                        if (result == JOptionPane.YES_OPTION) {
                            String str = (String) table.getValueAt(row, 0);
                            String str1 = (String) table.getValueAt(row, 1);
                            ResultSet rs = orderDao.chkBuy(con, usename, str, str1);
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(null, "请不要重复提交相同订单");
                                return;
                            }
                            orderDao.selBuy(con, usename, str, str1);
                            JOptionPane.showMessageDialog(null, "提交成功");
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

            }

        });
        table.setModel(new DefaultTableModel(new Object[][]{
        }, new String[]{
                "房源编号", "卖家号", "房源名称", "所属地址", "建筑单位", "朝向", "户型", "面积", "楼层", "单元号", "车库面积", "装修状况", "出租价格(元/月)", "出售价格(万元)", "租房", "购买"
        }));
        table.getColumnModel().getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.getColumnModel().getColumn(12).setPreferredWidth(125);
        table.getColumnModel().getColumn(13).setPreferredWidth(120);
        table.getColumnModel().getColumn(14).setPreferredWidth(50);
        table.getColumnModel().getColumn(15).setPreferredWidth(50);
        javax.swing.table.TableColumn tablecolumn3 = table.getColumn("租房");
        DefaultTableCellRenderer backGroundColor3 = new DefaultTableCellRenderer();
        backGroundColor3.setForeground(Color.BLUE);
        tablecolumn3.setCellRenderer(backGroundColor3);
        javax.swing.table.TableColumn tablecolumn31 = table.getColumn("购买");
        DefaultTableCellRenderer backGroundColor31 = new DefaultTableCellRenderer();
        backGroundColor31.setForeground(Color.BLUE);
        tablecolumn31.setCellRenderer(backGroundColor3);
        scrollPane.setViewportView(table);
        this.fillTable();
    }

    public void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        try (Connection con = jdbc.getConnection()) {
            ResultSet rs = HouseInfoDao.HouseList1(con, s, s1, s2, s3, s4, s5);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("H_no"));
                v.add(rs.getString("S_no"));
                v.add(rs.getObject("H_name"));
                v.add(rs.getObject("reg_ad"));
                v.add(rs.getObject("Item_cop"));
                v.add(rs.getObject("dir"));
                v.add(rs.getObject("Stru_na"));
                v.add(rs.getObject("area"));
                v.add(rs.getObject("floor"));
                v.add(rs.getObject("unit_no"));
                v.add(rs.getObject("cararea"));
                v.add(rs.getObject("fitment"));
                //v.add(rs.getObject("status"));
                v.add(rs.getObject("price"));
                v.add(rs.getObject("money"));
                v.add("租房");
                v.add("购买");
                dtm.addRow(v);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO: handle exception

    }
}
