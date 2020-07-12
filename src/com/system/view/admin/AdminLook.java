package com.system.view.admin;

import com.system.dao.AdminDao;
import com.system.model.SQLAdmin;
import com.system.util.StringUtil;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class AdminLook extends JInternalFrame {
    private final JTable tableAdmin;
    private final String a_id;
    Jdbc jdbcBean = new Jdbc();
    AdminDao admDao = new AdminDao();
    private final JTextField idtext;
    private final JTextField nametext;
    private final JTextField teltext;
    private final JTextField passwordtext;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminLook frame = new AdminLook(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminLook(String id) {
        setTitle("查询修改个人信息");
        setClosable(true);
        setIconifiable(true);
        //super();
        setBounds(0, 0, 990, 490);
        //setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        JPanel conjpe = new JPanel();
        conjpe.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(conjpe);
        conjpe.setLayout(null);

        JScrollPane Adminjsp = new JScrollPane();
        Adminjsp.setBounds(32, 45, 899, 141);
        conjpe.add(Adminjsp);
        tableAdmin = new JTable();
        //点击获取相应行的值
        tableAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tableAdmin.getSelectedRow();
                idtext.setText((String) tableAdmin.getValueAt(row, 0));
                nametext.setText((String) tableAdmin.getValueAt(row, 1));
                teltext.setText((String) tableAdmin.getValueAt(row, 2));
                passwordtext.setText((String) tableAdmin.getValueAt(row, 3));
            }
        });
        tableAdmin.setRowSelectionAllowed(false);
        tableAdmin.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "职工号", "姓名", "联系电话"
                }
        ));
        Adminjsp.setViewportView(tableAdmin);

        JLabel lblNewLabel = new JLabel("我的信息");
        lblNewLabel.setBounds(34, 0, 142, 45);
        conjpe.add(lblNewLabel);

        JButton btnNewButton = new JButton("我的职工号");
        btnNewButton.setBounds(34, 215, 107, 27);
        conjpe.add(btnNewButton);

        idtext = new JTextField();
        idtext.setEditable(false);
        idtext.setBounds(156, 216, 127, 24);
        conjpe.add(idtext);
        idtext.setColumns(10);
        idtext.setText(id);

        JButton btnNewButton_1 = new JButton("姓名");
        btnNewButton_1.setBounds(432, 215, 86, 27);
        conjpe.add(btnNewButton_1);

        nametext = new JTextField();
        nametext.setBounds(548, 216, 128, 24);
        conjpe.add(nametext);
        nametext.setColumns(10);

        JButton btnNewButton_2 = new JButton("电话");
        btnNewButton_2.setBounds(34, 272, 107, 27);
        conjpe.add(btnNewButton_2);

        teltext = new JTextField();
        teltext.setBounds(155, 273, 128, 24);
        conjpe.add(teltext);
        teltext.setColumns(10);

        passwordtext = new JTextField();
        passwordtext.setBounds(548, 273, 128, 24);
        conjpe.add(passwordtext);
        passwordtext.setColumns(10);

        JButton button = new JButton("密码");
        button.setBounds(432, 272, 86, 27);
        conjpe.add(button);

        JButton updatebutton = new JButton("修改");
        updatebutton.addActionListener(e -> {
            String id1 = idtext.getText();
            String name = nametext.getText();
            String telphone = teltext.getText();
            String password = passwordtext.getText();
            if (StringUtil.isEmpty(id1)) {
                JOptionPane.showMessageDialog(null, "请选择你的记录");
                return;
            }
            SQLAdmin sqlAdmin = new SQLAdmin(id1, name, telphone, password);
            Connection con = null;
            try {
                con = jdbcBean.getConnection();
                int Num = admDao.AdmUpdate(con, sqlAdmin);
                if (Num == 1) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    resetValue();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                try {
                    jdbcBean.closeCt(con);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        updatebutton.setBounds(206, 371, 96, 27);
        conjpe.add(updatebutton);

        JButton button_1 = new JButton("取消");
        button_1.addActionListener(arg0 -> { });
        button_1.setIcon(new ImageIcon("img\\reset.png"));
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetValue();
            }
        });
        button_1.setBounds(363, 371, 96, 27);
        conjpe.add(button_1);
        this.a_id = id;
        this.fillTableAdmain();
    }



    public void fillTableAdmain() {
        DefaultTableModel dtm2 = (DefaultTableModel) tableAdmin.getModel();
        dtm2.setRowCount(0);
        Connection conn = null;
        try {
            conn = jdbcBean.getConnection();
            ResultSet rs = admDao.AdminList(conn, a_id);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getObject("A_id"));//括号里的东西必须为数据库表格中所对应的属性
                v.add(rs.getObject("A_name"));
                v.add(rs.getObject("A_tel"));
                //v.add(rs.getObject("A_password"));
                dtm2.addRow(v);//塞入每一行记录
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcBean.closeCt(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetValue() {
        idtext.setText("");
        nametext.setText("");
        teltext.setText("");
        passwordtext.setText("");
    }
}