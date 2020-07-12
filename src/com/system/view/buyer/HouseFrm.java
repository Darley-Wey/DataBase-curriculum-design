package com.system.view.buyer;

import com.system.dao.HouseInfoDao;
import com.system.dao.OrderDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * @author Darley
 */
public class HouseFrm extends JFrame {

    private final JTable info;
    Jdbc jdbc = new Jdbc();
    HouseInfoDao houseInfoDao = new HouseInfoDao();
    OrderDao orderDao = new OrderDao();
    Connection con = jdbc.getConnection();

    /**
     * Launch the application.
     */
    //实例化另外一个窗口，以调用方法
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HouseFrm frame = new HouseFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     * 展示全部已审核未交易的房源
     */
    public HouseFrm(String usename) {
        setTitle("全部房源信息");
        this.setLocationRelativeTo(null);
        setBounds(100, 100, 1400, 700);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        Panel panel = new Panel();
        menuBar.add(panel);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        //表格展示房源信息
        info = new JTable();
        info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int row = info.rowAtPoint(point);
                int column = info.columnAtPoint(point);//获取选中列号
                if (column == 14) {
                    try {
                        int result = JOptionPane.showConfirmDialog(null, "确定提交租房订单？");
                        if (result == JOptionPane.YES_OPTION) {
                            String str = (String) info.getValueAt(row, 0);//房源编号
                            String str1 = (String) info.getValueAt(row, 1);//卖家注册号
                            ResultSet rs = orderDao.chkRent(con, usename, str, str1);//检查房源状态
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(null, "请不要重复提交相同订单");
                                return;
                            }
                            orderDao.selRent(con, usename, str, str1);//提交订单
                            JOptionPane.showMessageDialog(null, "提交成功");
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (column == 15) {
                    try {
                        int result = JOptionPane.showConfirmDialog(null, "确定提交买房订单？");
                        if (result == JOptionPane.YES_OPTION) {
                            String str = (String) info.getValueAt(row, 0);
                            String str1 = (String) info.getValueAt(row, 1);
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

        //表格
        info.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "房源编号", "卖家号", "房源名称", "所属地址", "建筑单位", "朝向", "户型", "面积", "楼层", "单元号", "车库面积", "装修状况", "出租价格(元/月)", "出售价格(万元)", "租房", "购买"
                }
        ));
        info.getColumnModel().getColumn(3).setPreferredWidth(125);
        info.getColumnModel().getColumn(5).setPreferredWidth(50);
        info.getColumnModel().getColumn(7).setPreferredWidth(50);
        info.getColumnModel().getColumn(8).setPreferredWidth(50);
        info.getColumnModel().getColumn(9).setPreferredWidth(50);
        info.getColumnModel().getColumn(12).setPreferredWidth(125);
        info.getColumnModel().getColumn(13).setPreferredWidth(120);
        info.getColumnModel().getColumn(14).setPreferredWidth(50);
        info.getColumnModel().getColumn(15).setPreferredWidth(50);
        TableColumn tableColumnRent = info.getColumn("租房");
        TableColumn tableColumnBuy = info.getColumn("购买");
       /*
         改变最后提交订单两列为蓝色
        */
        DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
        backGroundColor.setForeground(Color.BLUE);
        tableColumnRent.setCellRenderer(backGroundColor);
        tableColumnBuy.setCellRenderer(backGroundColor);
        scrollPane.setViewportView(info);
        this.fillTable();//填充表格
    }

    public void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) info.getModel();
        try (Connection con = jdbc.getConnection()) {
            ResultSet rs = houseInfoDao.houseList(con);
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
