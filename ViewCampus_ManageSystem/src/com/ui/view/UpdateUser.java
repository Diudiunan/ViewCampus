package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qin.dao.UserDao;
import com.qin.dao.UserEventDao;
import com.qin.dao.UserInformationDao;
import com.qin.entity.User;
import com.qin.entity.UserEvent;
import com.qin.entity.UserInformation;
import com.qin.jdbc.JDBCUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUser extends JFrame {

	private JPanel contentPane;
	private JTextField IDtext;
	private JTextField Accounttext;
	private JTextField Righttext;
	private JTextField Passtext;
	private JTextField Nametext;
	private JTextField Unittext;
	private JTextField UserEventtext;
	private JTextArea IntroductiontextArea;
	private User user;
	private UserInformation userInformation;
	private UserEvent userEvent;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public UserEvent getUserEvent() {
		return userEvent;
	}

	public void setUserEvent(UserEvent userEvent) {
		this.userEvent = userEvent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUser frame = new UpdateUser();
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
	public UpdateUser() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel IDLabel = new JLabel("ID");
		IDLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel AccountLabel = new JLabel("\u7528\u6237\u8D26\u53F7");
		AccountLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel RightLabel = new JLabel("\u7528\u6237\u6743\u9650");
		RightLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel PassLabel = new JLabel("\u7528\u6237\u901A\u8FC7");
		PassLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel NameLabel = new JLabel("\u7528\u6237\u540D");
		NameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel UnitLabel = new JLabel("\u7528\u6237\u5355\u4F4D");
		UnitLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel UserEventLabel = new JLabel("\u7528\u6237\u6D3B\u52A8");
		UserEventLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IntroductionLabel = new JLabel("\u7528\u6237\u4ECB\u7ECD");
		IntroductionLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		IDtext = new JTextField();
		IDtext.setEditable(false);
		IDtext.setColumns(10);
		
		Accounttext = new JTextField();
		Accounttext.setColumns(10);
		
		Righttext = new JTextField();
		Righttext.setColumns(10);
		
		Passtext = new JTextField();
		Passtext.setColumns(10);
		
		Nametext = new JTextField();
		Nametext.setColumns(10);
		
		Unittext = new JTextField();
		Unittext.setColumns(10);
		
		UserEventtext = new JTextField();
		UserEventtext.setEditable(false);
		UserEventtext.setColumns(10);
		
		IntroductiontextArea = new JTextArea();
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定修改！")==0) {
					if(IDtext.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "ID为空");
					}else {
						if(UpdateUser()) {
							JOptionPane.showMessageDialog(null, "修改成功");
						}
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(NameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(PassLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(IntroductionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AccountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(IDLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(RightLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(UnitLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(UserEventLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(IntroductiontextArea)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(UserEventtext)
							.addComponent(Unittext)
							.addComponent(Nametext)
							.addComponent(Passtext)
							.addComponent(Righttext)
							.addComponent(Accounttext)
							.addComponent(IDtext, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
					.addContainerGap(161, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(265, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(236))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(IDtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Accounttext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Righttext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Passtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PassLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Nametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(NameLabel))
							.addGap(18)
							.addComponent(Unittext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(UserEventtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(IDLabel)
							.addGap(18)
							.addComponent(AccountLabel)
							.addGap(18)
							.addComponent(RightLabel)
							.addGap(96)
							.addComponent(UnitLabel)
							.addGap(18)
							.addComponent(UserEventLabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(IntroductionLabel)
								.addComponent(IntroductiontextArea, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 0, Short.MAX_VALUE)))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(14))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	/**
	 * 初始化显示
	 * @return
	 */
	public Boolean ShowUserAllInformation() {
		if(user == null) {
			return false;
		}
		IDtext.setText(user.getUserId());
		Accounttext.setText(user.getUserAccount());
		Righttext.setText(Integer.toString(user.getUserRight()));
		Passtext.setText(Integer.toString(user.getUserPass()));
		Nametext.setText(userInformation.getUserName());
		Unittext.setText(userInformation.getUserUnit());
		IntroductiontextArea.setText(userInformation.getUserIntroduction());
		UserEventDao userEventDao = new UserEventDao();
		Connection connection = JDBCUtil.getConnection();
		try {
			userEvent = userEventDao.GetUserEvent(connection, user.getUserId());
			UserEventtext.setText(userEvent.getEventsId());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
			return false;
		} finally {
			JDBCUtil.close_rele_all(connection, null, null);
		}
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	private Boolean UpdateUser() {
		User user = new User();
		user.setUserId(IDtext.getText());
		user.setUserAccount(Accounttext.getText());
		user.setUserRight(Integer.valueOf(Righttext.getText()));
		user.setUserPass(Integer.valueOf(Passtext.getText()));
		UserInformation userInformation = new UserInformation();
		userInformation.setUserId(IDtext.getText());
		userInformation.setUserName(Nametext.getText());
		userInformation.setUserUnit(Unittext.getText());
		userInformation.setUserIntroduction(IntroductiontextArea.getText());
		UserDao userDao = new UserDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		Connection connection = JDBCUtil.getConnection();
		try {
			return userDao.UpdateUser(connection, user)==userInformationDao.UpdateUserInformation(connection, userInformation)==true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
			return false;
		} finally {
			JDBCUtil.close_rele_all(connection, null, null);
		}
	}
}
