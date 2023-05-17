package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qin.dao.AdminDao;
import com.qin.entity.Admin;
import com.qin.jdbc.JDBCUtil;
import com.ui.operation.LogInOperation;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminLogIn extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogIn frame = new AdminLogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogIn() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton LogInButton = new JButton("\u767B\u5F55");
		LogInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin(e);
			}
		});
		LogInButton.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel = new JLabel("\u89C6\u6821\u56ED");
		lblNewLabel.setFont(new Font("华文隶书", Font.PLAIN, 30));
		lblNewLabel.setIcon(new ImageIcon(AdminLogIn.class.getResource("/source/images/icons8-snake-48.png")));
		
		JLabel IDLabel = new JLabel("ID\uFF1A");
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IDLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel PassLabel = new JLabel("\u5BC6\u7801");
		PassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PassLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		IDField = new JTextField();
		IDField.setColumns(10);
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(118)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(IDLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(IDField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(PassLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordField))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(151)
							.addComponent(LogInButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(IDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IDLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PassLabel, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
					.addGap(48)
					.addComponent(LogInButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		this.setLocationRelativeTo(null);
	}

	//登录
	private boolean AdminLogin(ActionEvent e) {
		// TODO Auto-generated method stub
		//获得输入
		String adminId=this.IDField.getText();
		String adminPass=new String(this.passwordField.getPassword());
		if(LogInOperation.IsEmpty(adminId)) {
			JOptionPane.showMessageDialog(null, "ID为空！");
			return false;
		}
		if(LogInOperation.IsEmpty(adminPass)) {
			JOptionPane.showMessageDialog(null, "密码为空！");
			return false;
		}
		AdminDao admindao = new AdminDao();
		Connection con = JDBCUtil.getConnection();
		Admin admin = new Admin(adminId,adminPass);
		try {
			if(admindao.AdminLogIn(con, admin)) {
				AdminMain adminMain = new AdminMain();
				adminMain.setVisible(true);
				this.dispose();
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "请检查输入！");
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
			return false;
		} finally {
			JDBCUtil.close_rele_all(con, null, null);
		}
	}
}
