package com.system.view.seller;

import com.system.dao.RentInfoDao;
import com.system.model.Rentinfo;
import com.system.util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class SearchRentInterFrm extends JInternalFrame {
    private final JTextField Txt;
    private final JTable table;
    DbUtil dbUtil = new DbUtil();
    RentInfoDao rentinfoDao = new RentInfoDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRentInterFrm frame = new SearchRentInterFrm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public SearchRentInterFrm() {

    	  setTitle("查询求租信息");
          setClosable(true);
          setBounds(100, 100, 1042, 500);
          getContentPane().setLayout(null);
          JScrollPane scrollPane = new JScrollPane();
          scrollPane.setBounds(14, 78, 996, 373);
          getContentPane().add(scrollPane);
          table = new JTable();
          table.setModel(new DefaultTableModel(
                  new Object[][]{
                  },
                  new String[]{
                          "求租编号", "买家用户号", "区域", "具体补充", "支付方式", "联系电话", "房源状况", "理想价格(元/月)"
                  }
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(89);
        table.getColumnModel().getColumn(2).setPreferredWidth(118);
        table.getColumnModel().getColumn(3).setPreferredWidth(159);
        table.getColumnModel().getColumn(5).setPreferredWidth(144);
        table.getColumnModel().getColumn(6).setPreferredWidth(226);
        table.getColumnModel().getColumn(7).setPreferredWidth(148);
        scrollPane.setViewportView(table);
//		重写一个点进去查询所有的函数

        JLabel label = new JLabel("求租编号：");
        label.setBounds(33, 29, 79, 36);
        getContentPane().add(label);

        Txt = new JTextField();
        Txt.setBounds(126, 26, 109, 39);
        getContentPane().add(Txt);
        Txt.setColumns(10);

        JButton button = new JButton("查询");//"查询"按钮的action
        button.addActionListener(e -> {
            String h_no = Txt.getText();
            Rentinfo houseinfo = new Rentinfo();
            houseinfo.setR_no(h_no);
            fillTable(houseinfo);  //空查询所有，否则查询指定房源编号的信息
        });
        button.setBounds(297, 25, 98, 40);
        getContentPane().add(button);


    }


    /**
     * @param rentinfo 空查询所有，否则查询指定房源编号的信息
     */
    public void fillTable(Rentinfo rentinfo) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con;
        try {
            con = dbUtil.getCon();
            ResultSet rs = rentinfoDao.rentinfoList(con, rentinfo);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("R_no"));
                v.add(rs.getString("B_no"));
                v.add(rs.getString("R_ad"));
                v.add(rs.getString("R_add"));
                v.add(rs.getString("R_sty"));
                v.add(rs.getString("B_tel"));
                v.add(rs.getString("R_req"));
                v.add(rs.getString("R_money"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
