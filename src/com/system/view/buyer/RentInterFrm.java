package com.system.view.buyer;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.system.dao.RentInfoDao;
import com.system.model.Rentinfo;
import com.system.util.DbUtil;
import com.system.util.StringUtil;

public class RentInterFrm extends JFrame {
    private final JTextField R_noTxt;
    private final JTextField S_noTxt;
    private final JTextField reg_adTxt;
    private final JTextField addTxt;
    private final JTextField styTxt;
    private final JTextField telTxt;
    private final JTextField statusTxt;
    private final JTextField moneyTxt;
    DbUtil dbUtil = new DbUtil();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RentInterFrm frame = new RentInterFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Create the frame.
     */
    public RentInterFrm(String usename) {
    	  setTitle("发布求租信息");

          setBounds(new Rectangle(110, 110, 180, 120));
          setFocusable(true);
          setBounds(100, 100, 709, 463);
          getContentPane().setLayout(null);

          JLabel lblNewLabel = new JLabel("* 求租编号");
          lblNewLabel.setBounds(38, 10, 88, 29);
          getContentPane().add(lblNewLabel);

          JLabel label = new JLabel("* 区域");
          label.setBounds(50, 134, 54, 29);
          getContentPane().add(label);

          JLabel label_2 = new JLabel("具体补充描述");
          label_2.setBounds(25, 310, 101, 32);
          getContentPane().add(label_2);

          JLabel label_4 = new JLabel("支付方式");
          label_4.setBounds(38, 219, 66, 37);
          getContentPane().add(label_4);

          JLabel label_6 = new JLabel("* 联系电话");
          label_6.setBounds(38, 91, 78, 30);
          getContentPane().add(label_6);

          JLabel label_10 = new JLabel("房源状况需求");
          label_10.setBounds(25, 269, 101, 28);
          getContentPane().add(label_10);

          JLabel label_11 = new JLabel("* 理想价格(元/月）");
        label_11.setBounds(14, 176, 138, 30);
        getContentPane().add(label_11);

        R_noTxt = new JTextField();
        R_noTxt.setBounds(160, 6, 138, 33);
        getContentPane().add(R_noTxt);
        R_noTxt.setColumns(10);


        S_noTxt = new JTextField();
        S_noTxt.setBounds(160, 41, 138, 33);
        S_noTxt.setEditable(false);
        getContentPane().add(S_noTxt);
        S_noTxt.setColumns(10);
        S_noTxt.setText(usename);

        reg_adTxt = new JTextField();
        reg_adTxt.setBounds(160, 132, 138, 30);
        getContentPane().add(reg_adTxt);
        reg_adTxt.setColumns(10);

        addTxt = new JTextField();
        addTxt.setBounds(160, 308, 138, 34);
        getContentPane().add(addTxt);
        addTxt.setColumns(10);

        styTxt = new JTextField();
        styTxt.setBounds(160, 215, 138, 32);
        getContentPane().add(styTxt);
        styTxt.setColumns(10);

        telTxt = new JTextField();
        telTxt.setBounds(160, 87, 138, 32);
        getContentPane().add(telTxt);
        telTxt.setColumns(10);

        statusTxt = new JTextField();
        statusTxt.setBounds(160, 265, 138, 30);
        getContentPane().add(statusTxt);
        statusTxt.setColumns(10);

        moneyTxt = new JTextField();
        moneyTxt.setBounds(160, 172, 138, 30);
        getContentPane().add(moneyTxt);
        moneyTxt.setColumns(10);


        JButton jb_Add = new JButton("发布");
        jb_Add.addActionListener(e -> {
            String R_no = R_noTxt.getText();
            String S_no = S_noTxt.getText();
            String reg_ad = reg_adTxt.getText();
            String add = addTxt.getText();
            String sty = styTxt.getText();
            String tel = telTxt.getText();
            String status = statusTxt.getText();
            String money = moneyTxt.getText();
            if (StringUtil.isEmpty(R_no)) {
                JOptionPane.showMessageDialog(null, "求租编号不能为空");
                return;
            }

            if (StringUtil.isEmpty(reg_ad)) {
                JOptionPane.showMessageDialog(null, "求租区域不能为空");
                return;
            }
            if (StringUtil.isEmpty(tel)) {
                JOptionPane.showMessageDialog(null, "联系电话不能为空");
                return;
            }


            if (StringUtil.isEmpty(money)) {
                JOptionPane.showMessageDialog(null, "理想价格不能为空");
                return;
            }
            Rentinfo houseinfo = new Rentinfo(R_no, S_no, tel, reg_ad, money, sty, status, add);

            try {
                int n = RentInfoDao.rentInfoAdd(dbUtil.getCon(), houseinfo);
                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "发布求租成功");
                    R_noTxt.setText("");
                    S_noTxt.setText(usename);
                    reg_adTxt.setText("");
                    addTxt.setText("");
                    styTxt.setText("");
                    telTxt.setText("");
                    statusTxt.setText("");
                    moneyTxt.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "房源添加失败");
                }
            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, "房源添加失败");
                e1.printStackTrace();
            }
        });
        jb_Add.setBounds(444, 102, 95, 34);
        getContentPane().add(jb_Add);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.addActionListener(e -> {
            R_noTxt.setText("");
            S_noTxt.setText(usename);
            reg_adTxt.setText("");
            addTxt.setText("");
            styTxt.setText("");
            telTxt.setText("");
            statusTxt.setText("");
            moneyTxt.setText("");

        });
        btnNewButton.setBounds(444, 258, 95, 34);
        getContentPane().add(btnNewButton);

        JLabel label_12 = new JLabel("* 买家用户号");
        label_12.setBounds(38, 45, 108, 33);
        getContentPane().add(label_12);

    }

}
