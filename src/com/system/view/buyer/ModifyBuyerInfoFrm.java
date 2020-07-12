package com.system.view.buyer;

//import java.awt.BorderLayout;

import com.system.dao.BuyerDao;
import jdbc.Jdbc;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModifyBuyerInfoFrm extends JFrame {

	private final JPasswordField passwordField;
	private final JTextField textFieldName;
	private final JTextField textFieldEmail;
	private final JTextField textFieldAddr;
	private final JTextField textFieldTel;
	Jdbc jdbc=new Jdbc();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ModifyBuyerInfoFrm frame = new ModifyBuyerInfoFrm(null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifyBuyerInfoFrm(String usename) {
		setTitle("修改买家个人信息");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(410, 155, 605, 490);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelPass = new JLabel("密码");
		labelPass.setFont(new java.awt.Font("楷体", Font.BOLD, 20));
		passwordField = new JPasswordField();
		 
		
		JLabel labelName = new JLabel("姓名");
		labelName.setFont(new java.awt.Font("楷体", Font.BOLD, 20));
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 20));
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel labelAddr = new JLabel("地址");
		labelAddr.setFont(new java.awt.Font("楷体", Font.BOLD, 20));
		textFieldAddr = new JTextField();
		textFieldAddr.setColumns(10);
		

		
		JLabel labelTel = new JLabel("电话");
		labelTel.setFont(new java.awt.Font("楷体", Font.BOLD, 20));
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		
		//Connection con=null;
		JButton button = new JButton("确定");
		button.addActionListener(e -> {
			try (Connection con = jdbc.getConnection()) {
				int valuex = JOptionPane.showConfirmDialog(ModifyBuyerInfoFrm.this,
						"你确认要修改吗？", "请确认",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (valuex == JOptionPane.YES_OPTION) {
					String bPass = new String(passwordField.getPassword());
					String bName = textFieldName.getText();
					String bEmail = textFieldEmail.getText();
					String bAddr = textFieldAddr.getText();
					String bTel = textFieldTel.getText();
					BuyerDao buyerDao=new BuyerDao();
					buyerDao.buyerModify(con,bName,bPass,bEmail,bAddr,bTel,usename);
					JOptionPane.showMessageDialog(null, "修改成功");
					passwordField.setText("");
					textFieldName.setText("");
					textFieldEmail.setText("");
					textFieldAddr.setText("");
					textFieldTel.setText("");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO: handle exception
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(154)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(labelEmail)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(labelAddr)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelName)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(labelTel, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
										.addGap(18))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(labelPass)
										.addGap(24))))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(textFieldTel, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(textFieldAddr, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
					.addGap(193))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(269, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(228))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(122)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEmail)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPass)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelName))
							.addGap(6)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAddr))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelTel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(118, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
