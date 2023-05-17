package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.qin.dao.UserMessageDao;
import com.qin.entity.UserMessage;
import com.qin.jdbc.JDBCUtil;
import com.qin.util.MessageIdFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckMessage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextArea textArea;
	private List<UserMessage> userMessagesList;
	private int checkRow = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckMessage frame = new CheckMessage();
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
	public CheckMessage() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 836, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u67E5\u627E\u7528\u6237\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "ID为空");
				}else {
					SearchUserMessage();
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u6062\u590D");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecoverTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u6D88\u606F\u5185\u5BB9");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textArea = new JTextArea();
		
		JLabel lblNewLabel_2 = new JLabel("\u8868:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_2))))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressedShowMessage(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MID", "UID", "\u6D88\u606F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(242);
		table.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		//初始化
		this.ShowUserMessage();
	}
	
	/**
	 * 初始化
	 * @return
	 */
	private Boolean ShowUserMessage() {
		textField.setText(MessageIdFactory.GetTableLabel());
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		UserMessageDao userMessageDao = new UserMessageDao();
		try {
			List<UserMessage> uMList = userMessageDao.GetMessageList(connection);
			userMessagesList = uMList;
			int numL = uMList.size();
			for(int i=0;i<numL;i++) {
				Vector<Object> list = new Vector<>();
				list.add(uMList.get(i).getMessageId());
				list.add(uMList.get(i).getUserId());
				list.add(uMList.get(i).getMessage());
				defaultTableModel.addRow(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
			return false;
		}finally {
			JDBCUtil.close_rele_all(connection, null, null);
		}
		return true;
	}
	
	/**
	 * 选中活动事件
	 * @param e
	 */
	private void MousePressedShowMessage(MouseEvent e) {
		int row = table.getSelectedRow();
		textArea.setText((String)table.getValueAt(row, 2));
		checkRow = row;
	}
	
	/**
	 * 恢复table
	 */
	private void RecoverTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		int numL = userMessagesList.size();
		for(int i=0;i<numL;i++) {
			Vector<Object> list = new Vector<>();
			list.add(userMessagesList.get(i).getMessageId());
			list.add(userMessagesList.get(i).getUserId());
			list.add(userMessagesList.get(i).getMessage());
			defaultTableModel.addRow(list);
		}
	}
	
	/**
	 * 查询用户的系统消息
	 * @return
	 */
	private Boolean SearchUserMessage() {
		String uMString = textField_1.getText();
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		UserMessageDao userMessageDao = new UserMessageDao();
		try {
			List<UserMessage> uMList = userMessageDao.GetUserMessageList(connection, uMString);
			int numL = uMList.size();
			for(int i=0;i<numL;i++) {
				Vector<Object> list = new Vector<>();
				list.add(uMList.get(i).getMessageId());
				list.add(uMList.get(i).getUserId());
				list.add(uMList.get(i).getMessage());
				defaultTableModel.addRow(list);
			}
			return true;
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
