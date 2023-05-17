package com.ui.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.qin.dao.UserDao;
import com.qin.dao.UserInformationDao;
import com.qin.entity.User;
import com.qin.entity.UserInformation;
import com.qin.jdbc.JDBCUtil;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField FindIdField;
	private JTextField SelectIdField;
	private List<User> userList;
	private List<UserInformation> userInformationList;
	private int checkRow = -1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckUser frame = new CheckUser();
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
	public CheckUser() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 849, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u67E5\u627E\u7528\u6237\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		FindIdField = new JTextField();
		FindIdField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FindIdField.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "ID为空");
				}else {
					SearchUser();
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u9009\u4E2D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		SelectIdField = new JTextField();
		SelectIdField.setEditable(false);
		SelectIdField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u548C\u4FEE\u6539");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkRow!=-1) {
					UpdateUser updateUser = new UpdateUser();
					updateUser.setUser(userList.get(checkRow));
					updateUser.setUserInformation(userInformationList.get(checkRow));
					updateUser.ShowUserAllInformation();
					updateUser.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "请选择用户！");
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_2 = new JButton("\u6062\u590D");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecoverTable();
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkRow!=-1) {
					SentMessage sentMessage = new SentMessage();
					sentMessage.setuIDString(SelectIdField.getText());
					sentMessage.ShowID();
					sentMessage.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "请选择用户！");
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(SelectIdField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
							.addGap(70)
							.addComponent(btnNewButton_1)
							.addGap(29)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(27)
							.addComponent(FindIdField, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(FindIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_2)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(SelectIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressedShowUser(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"UID", "\u7528\u6237\u8D26\u53F7", "\u7528\u6237\u540D", "\u7528\u6237\u6743\u9650", "\u7528\u6237\u901A\u884C", "\u7528\u6237\u5355\u4F4D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setPreferredWidth(99);
		table.getColumnModel().getColumn(3).setPreferredWidth(62);
		table.getColumnModel().getColumn(4).setPreferredWidth(58);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		//初始化显示
		this.ShowUser();
	}

	/**
	 * 初始化显示
	 * @return
	 */
	private Boolean ShowUser() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		UserDao userDao = new UserDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		try {
			List<User> uList = userDao.GetUserList(connection);
			List<UserInformation> uListI = userInformationDao.GetUserInformationList(connection);
			userList = uList;
			userInformationList = uListI;
			int numL = uList.size();
			for(int i=0;i<numL;i++) {
				Vector<Object> list = new Vector<>();
				list.add(uList.get(i).getUserId());
				list.add(uList.get(i).getUserAccount());
				list.add(uListI.get(i).getUserName());
				list.add(uList.get(i).getUserPass());
				list.add(uList.get(i).getUserRight());
				list.add(uListI.get(i).getUserUnit());
				defaultTableModel.addRow(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "发生错误！");
		}finally {
			JDBCUtil.close_rele_all(connection, null, null);
		}
		return true;
	}
	
	/**
	 * 选中活动事件
	 * @param e
	 */
	private void MousePressedShowUser(MouseEvent e) {
		int row = table.getSelectedRow();
		SelectIdField.setText((String)table.getValueAt(row, 0));
		checkRow = row;
	}
	
	/**
	 * 恢复table
	 */
	private void RecoverTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		int numL = userList.size();
		for(int i=0;i<numL;i++) {
			Vector<Object> list = new Vector<>();
			list.add(userList.get(i).getUserId());
			list.add(userList.get(i).getUserAccount());
			list.add(userInformationList.get(i).getUserName());
			list.add(userList.get(i).getUserPass());
			list.add(userList.get(i).getUserRight());
			list.add(userInformationList.get(i).getUserUnit());
			defaultTableModel.addRow(list);
		}
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	private Boolean SearchUser() {
		String uID = FindIdField.getText();
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		UserDao userDao = new UserDao();
		UserInformationDao userInformationDao = new UserInformationDao();
		try {
			User user = userDao.GetUserInformation(connection, uID);
			UserInformation userInformation = userInformationDao.GetUserInformation(connection, uID);
			Vector<Object> list = new Vector<>();
			list.add(user.getUserId());
			list.add(user.getUserAccount());
			list.add(userInformation.getUserName());
			list.add(user.getUserPass());
			list.add(user.getUserRight());
			list.add(userInformation.getUserUnit());
			defaultTableModel.addRow(list);
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
