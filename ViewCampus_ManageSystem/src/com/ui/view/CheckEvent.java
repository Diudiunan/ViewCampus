package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.qin.dao.EventDao;
import com.qin.dao.EventInformationDao;
import com.qin.entity.Event;
import com.qin.entity.EventInformation;
import com.qin.jdbc.JDBCUtil;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckEvent extends JFrame {

	private JPanel contentPane;
	private JTextField FindIdtext;
	private JTable table;
	private JTextField SelectIdtext;
	private List<Event> eventList;
	private List<EventInformation> eventInformationList;
	private int checkRow = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckEvent frame = new CheckEvent();
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
	public CheckEvent() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u67E5\u627E\u6D3B\u52A8\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		FindIdtext = new JTextField();
		FindIdtext.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FindIdtext.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "ID为空");
				}else {
					SearchEvent();
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u6062\u590D");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecoverTable();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u9009\u4E2D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		SelectIdtext = new JTextField();
		SelectIdtext.setEditable(false);
		SelectIdtext.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u548C\u4FEE\u6539");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkRow!=-1) {
					UpdateEvent updateEvent = new UpdateEvent();
					updateEvent.setEvent(eventList.get(checkRow));
					updateEvent.setEventInformation(eventInformationList.get(checkRow));
					updateEvent.ShowEventAllInformation();
					updateEvent.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "请选择用户！");
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SelectIdtext, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
							.addGap(115)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(FindIdtext, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(FindIdtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_2)))
					.addGap(17)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(SelectIdtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MousePressedShowEvent(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EID", "UID", "\u6D3B\u52A8\u540D", "\u6D3B\u52A8\u4E3E\u529E\u65B9", "\u901A\u8FC7\u72B6\u6001", "\u7ED3\u675F\u72B6\u6001", "\u7ED3\u675F\u65F6\u95F4"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(74);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(58);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		//初始化
		this.ShowEvent();
	}
	
	/**
	 * 初始化
	 * @return
	 */
	private Boolean ShowEvent() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		EventDao eventDao = new EventDao();
		EventInformationDao eventInformationDao = new EventInformationDao();
		try {
			List<Event> eList = eventDao.GetEventList(connection);
			List<EventInformation> eIList = eventInformationDao.GetEventList(connection);
			eventList = eList;
			eventInformationList = eIList;
			int numL = eList.size();
			for(int i=0;i<numL;i++) {
				Vector<Object> list = new Vector<>();
				list.add(eList.get(i).getEventId());
				list.add(eList.get(i).getUserId());
				list.add(eList.get(i).getEventName());
				list.add(eList.get(i).getEventSponsor());
				list.add(eList.get(i).getStatusApproval());
				list.add(eList.get(i).getStatusEnd());
				list.add(eIList.get(i).getEventEnd());
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
	private void MousePressedShowEvent(MouseEvent e) {
		int row = table.getSelectedRow();
		SelectIdtext.setText((String)table.getValueAt(row, 0));
		checkRow = row;
	}
	
	/**
	 * 恢复table
	 */
	private void RecoverTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		int numL = eventList.size();
		for(int i=0;i<numL;i++) {
			Vector<Object> list = new Vector<>();
			list.add(eventList.get(i).getEventId());
			list.add(eventList.get(i).getUserId());
			list.add(eventList.get(i).getEventName());
			list.add(eventList.get(i).getEventSponsor());
			list.add(eventList.get(i).getStatusApproval());
			list.add(eventList.get(i).getStatusEnd());
			list.add(eventInformationList.get(i).getEventEnd());
			defaultTableModel.addRow(list);
		}
	}
	
	/**
	 * 查询活动
	 * @return
	 */
	private Boolean SearchEvent() {
		String eID = FindIdtext.getText();
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		Connection connection = JDBCUtil.getConnection();
		EventDao eventDao = new EventDao();
		EventInformationDao eventInformationDao = new EventInformationDao();
		try {
			Event event = eventDao.GetEvent(connection, eID);
			EventInformation eventInformation = eventInformationDao.GetEventInformation(connection, eID);
			Vector<Object> list = new Vector<>();
			list.add(event.getEventId());
			list.add(event.getUserId());
			list.add(event.getEventName());
			list.add(event.getEventSponsor());
			list.add(event.getStatusApproval());
			list.add(event.getStatusEnd());
			list.add(eventInformation.getEventEnd());
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
