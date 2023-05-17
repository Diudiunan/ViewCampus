package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qin.dao.UserMessageDao;
import com.qin.entity.UserMessage;
import com.qin.jdbc.JDBCUtil;
import com.qin.util.MessageIdFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SentMessage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private String uIDString;
	

	public String getuIDString() {
		return uIDString;
	}

	public void setuIDString(String uIDString) {
		this.uIDString = uIDString;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentMessage frame = new SentMessage();
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
	public SentMessage() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblUid = new JLabel("UID:");
		lblUid.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u6D88\u606F\u5185\u5BB9\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textArea = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("\u53D1\u9001");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "是否发送！")==0) {
					if(SentMessage()) {
						JOptionPane.showConfirmDialog(null, "发送成功");
					}else {
						JOptionPane.showConfirmDialog(null, "发送失败");
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(75)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUid, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(160, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(204, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(184))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUid, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	//显示UID
	public Boolean ShowID() {
		if(uIDString==null || uIDString.length()==0) {
			return false;
		}else {
			textField.setText(uIDString);
			return true;
		}
	}
	
	/**
	 * 发送系统消息
	 * @return
	 */
	private Boolean SentMessage() {
		String addString = "管理员->";
		UserMessage userMessage = new UserMessage();
		userMessage.setMessageId(MessageIdFactory.GetMID());
		userMessage.setUserId(textField.getText());
		userMessage.setMessage(addString+textArea.getText());
		Connection connection = JDBCUtil.getConnection();
		UserMessageDao userMessageDao = new UserMessageDao();
		try {
			return userMessageDao.SaveSentMessage(connection, userMessage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
			return false;
		}finally {
			JDBCUtil.close_rele_all(connection, null, null);
		}
	}
}
