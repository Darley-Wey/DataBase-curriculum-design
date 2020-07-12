package com.system.view.seller;

import com.system.dao.HouseInfoDao;
import com.system.model.Houseinfo;
import com.system.util.DbUtil;
import com.system.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HouseInfoInterFrm extends JInternalFrame {
    private final JTextField H_noTxt;
    private final JTextField H_nameTxt;
    private final JTextField S_noTxt;
    private final JTextField reg_adTxt;
    private final JTextField Item_copTxt;
    private final JTextField areaTxt;
    private final JTextField floorTxt;
    private final JTextField unit_noTxt;
    private final JTextField carareaTxt;
    private final JTextField priceTxt;
    private final JTextField moneyTxt;
    DbUtil dbUtil = new DbUtil();
    HouseInfoDao houseinfoDao = new HouseInfoDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HouseInfoInterFrm frame = new HouseInfoInterFrm(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Create the frame.
     */
    public HouseInfoInterFrm(String usename) {
    	 setTitle("发布房源信息");

         setNormalBounds(new Rectangle(0, 0, 80, 120));
         setClosable(true);
         setBounds(100, 100, 650, 540);
         getContentPane().setLayout(null);

         JLabel lblNewLabel = new JLabel("* 房源编号");
         lblNewLabel.setBounds(45, 11, 78, 29);
         getContentPane().add(lblNewLabel);

         JLabel lblNewLabel_1 = new JLabel("  房源名称");
         lblNewLabel_1.setBounds(45, 54, 78, 22);
         getContentPane().add(lblNewLabel_1);

         JLabel label = new JLabel("* 所属地址");
         label.setBounds(45, 100, 78, 22);
         getContentPane().add(label);

         JLabel label_1 = new JLabel("建筑单位");
         label_1.setBounds(331, 57, 78, 16);
         getContentPane().add(label_1);

         JLabel label_2 = new JLabel("朝向");
         label_2.setBounds(69, 135, 54, 24);
         getContentPane().add(label_2);

         JLabel label_3 = new JLabel("户型");
         label_3.setBounds(69, 172, 54, 23);
         getContentPane().add(label_3);

         JLabel lblm = new JLabel("* 面积(m²)");
         lblm.setBounds(45, 208, 78, 22);
         getContentPane().add(lblm);

         JLabel label_5 = new JLabel("* 楼层");
         label_5.setBounds(55, 248, 54, 31);
         getContentPane().add(label_5);

         JLabel label_6 = new JLabel("单元号");
         label_6.setBounds(65, 300, 54, 22);
         getContentPane().add(label_6);

         JLabel lblm_1 = new JLabel("车库面积(m²)");
         lblm_1.setBounds(45, 335, 100, 29);
         getContentPane().add(lblm_1);

         JLabel label_8 = new JLabel("装修状况");
         label_8.setBounds(60, 377, 63, 29);
         getContentPane().add(label_8);

         JLabel label_9 = new JLabel("* 出租价格（元/月）");
         label_9.setBounds(23, 419, 130, 22);
         getContentPane().add(label_9);

         JLabel label_11 = new JLabel("* 出售价格（万元）");
        label_11.setBounds(23, 454, 130, 29);
        getContentPane().add(label_11);

        H_noTxt = new JTextField();
        H_noTxt.setBounds(162, 11, 116, 29);
        getContentPane().add(H_noTxt);
        H_noTxt.setColumns(10);


        S_noTxt = new JTextField();
        S_noTxt.setBounds(423, 11, 116, 31);
        S_noTxt.setEditable(false);
        getContentPane().add(S_noTxt);
        S_noTxt.setColumns(10);
        S_noTxt.setText(usename);

        H_nameTxt = new JTextField();
        H_nameTxt.setBounds(162, 53, 116, 29);
        getContentPane().add(H_nameTxt);
        H_nameTxt.setColumns(10);

        reg_adTxt = new JTextField();
        reg_adTxt.setBounds(162, 93, 116, 29);
        getContentPane().add(reg_adTxt);
        reg_adTxt.setColumns(10);

        Item_copTxt = new JTextField();
        Item_copTxt.setBounds(423, 52, 116, 30);
        getContentPane().add(Item_copTxt);
        Item_copTxt.setColumns(10);

        areaTxt = new JTextField();
        areaTxt.setBounds(162, 208, 116, 29);
        getContentPane().add(areaTxt);
        areaTxt.setColumns(10);

        floorTxt = new JTextField();
        floorTxt.setBounds(162, 249, 116, 29);
        getContentPane().add(floorTxt);
        floorTxt.setColumns(10);

        unit_noTxt = new JTextField();
        unit_noTxt.setBounds(165, 291, 116, 31);
        getContentPane().add(unit_noTxt);
        unit_noTxt.setColumns(10);

        carareaTxt = new JTextField();
        carareaTxt.setBounds(165, 335, 116, 29);
        getContentPane().add(carareaTxt);
        carareaTxt.setColumns(10);

        priceTxt = new JTextField();
        priceTxt.setBounds(165, 419, 116, 29);
        getContentPane().add(priceTxt);
        priceTxt.setColumns(10);

        moneyTxt = new JTextField();
        moneyTxt.setBounds(165, 456, 116, 29);
        getContentPane().add(moneyTxt);
        moneyTxt.setColumns(10);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"朝阳", "朝阴"}));
        comboBox.setBounds(162, 135, 116, 29);
        getContentPane().add(comboBox);

        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[]{"单居室", "两室一厅", "三室一厅", "三室两厅", "四室两厅"}));
        comboBox_1.setBounds(162, 172, 116, 29);
        getContentPane().add(comboBox_1);

        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[]{"简装", "毛坯", "精装"}));
        comboBox_2.setBounds(165, 377, 116, 29);
        getContentPane().add(comboBox_2);

        JButton jb_Add = new JButton("发布");
        jb_Add.addActionListener(e -> {
            String H_no = H_noTxt.getText();
            String S_no = S_noTxt.getText();
            String H_name = H_nameTxt.getText();
            String reg_ad = reg_adTxt.getText();
            String Item_cop = Item_copTxt.getText();
            String dir = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            String Stru_na = Objects.requireNonNull(comboBox_1.getSelectedItem()).toString();
            String area = areaTxt.getText();
            String floor = floorTxt.getText();
            String unit_no = unit_noTxt.getText();
            String cararea = carareaTxt.getText();
            String fitment = Objects.requireNonNull(comboBox_2.getSelectedItem()).toString();
            String price = priceTxt.getText();
            String facticity = "未审核";
            String status = "未交易";
            String money = moneyTxt.getText();
            if (StringUtil.isEmpty(H_no)) {
                JOptionPane.showMessageDialog(null, "房源编号不能为空");
                return;
            }
            if (StringUtil.isEmpty(S_no)) {
                JOptionPane.showMessageDialog(null, "卖家用户号不能为空");
                return;
            }

            if (StringUtil.isEmpty(reg_ad)) {
                JOptionPane.showMessageDialog(null, "房源所属地址不能为空");
                return;
            }
            if (StringUtil.isEmpty(area)) {
                JOptionPane.showMessageDialog(null, "建筑面积不能为空");
                return;
            }
            if (StringUtil.isEmpty(floor)) {
                JOptionPane.showMessageDialog(null, "楼层不能为空");
                return;
            }
            if (StringUtil.isEmpty(price)) {
                JOptionPane.showMessageDialog(null, "出租价格不能为空");
                return;
            }

            if (StringUtil.isEmpty(money)) {
                JOptionPane.showMessageDialog(null, "出售价格不能为空");
                return;
            }
            Houseinfo houseinfo = new Houseinfo(H_no, S_no, H_name, reg_ad, Item_cop, dir, Stru_na, area, floor, unit_no, cararea, fitment, facticity, status, price, money);

            try {
                int n = houseinfoDao.houseinfoAdd(dbUtil.getCon(), houseinfo);
                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "房源添加成功");
                    H_noTxt.setText("");
                    S_noTxt.setText(usename);
                    H_nameTxt.setText("");
                    reg_adTxt.setText("");
                    Item_copTxt.setText("");
                    comboBox.setSelectedItem("朝阳");
                    comboBox_1.setSelectedItem("单居室");
                    areaTxt.setText("");
                    floorTxt.setText("");
                    unit_noTxt.setText("");
                    carareaTxt.setText("");
                    comboBox_2.setSelectedItem("简装");
                    priceTxt.setText("");
                    moneyTxt.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "房源添加失败");
                }
            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, "房源添加失败");
                e1.printStackTrace();
            }
        });
        jb_Add.setBounds(405, 214, 78, 44);
        getContentPane().add(jb_Add);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.addActionListener(e -> {
            H_noTxt.setText("");
            S_noTxt.setText(usename);
            H_nameTxt.setText("");
            reg_adTxt.setText("");
            Item_copTxt.setText("");
            comboBox.setSelectedItem("朝阳");
            comboBox_1.setSelectedItem("单居室");
            areaTxt.setText("");
            floorTxt.setText("");
            unit_noTxt.setText("");
            carareaTxt.setText("");
            comboBox_2.setSelectedItem("简装");
            priceTxt.setText("");
            moneyTxt.setText("");

        });
        btnNewButton.setBounds(405, 327, 78, 44);
        getContentPane().add(btnNewButton);

        JLabel label_12 = new JLabel("卖家用户号");
        label_12.setBounds(331, 18, 78, 22);
        getContentPane().add(label_12);


    }
}
