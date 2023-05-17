package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qin.dao.EventDao;
import com.qin.dao.EventInformationDao;
import com.qin.dao.UserEventDao;
import com.qin.dao.UserMessageDao;
import com.qin.entity.Event;
import com.qin.entity.EventInformation;
import com.qin.jdbc.JDBCUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateEvent extends JFrame {

	private JPanel contentPane;
	private JTextField EIDtext;
	private JTextField UIDtext;
	private JTextField Nametext;
	private JTextField Sponsortext;
	private JTextField Approvaltext;
	private JTextField Endtext;
	private JTextField Sitetext;
	private JTextField StartTimetext;
	private JTextField EndTimetext;
	private JTextArea IntroductiontextArea;
	private Event event;
	private EventInformation eventInformation;
	
	

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventInformation getEventInformation() {
		return eventInformation;
	}

	public void setEventInformation(EventInformation eventInformation) {
		this.eventInformation = eventInformation;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEvent frame = new UpdateEvent();
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
	public UpdateEvent() {
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel EIDLabel = new JLabel("EID");
		EIDLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel UIDLabel = new JLabel("UID");
		UIDLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_2 = new JLabel("\u6D3B\u52A8\u540D");
		IDLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_3 = new JLabel("\u4E3E\u529E\u65B9");
		IDLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4 = new JLabel("\u6D3B\u52A8\u901A\u8FC7");
		IDLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4_1 = new JLabel("\u6D3B\u52A8\u7ED3\u675F");
		IDLabel_4_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4_1_1 = new JLabel("\u6D3B\u52A8\u5730\u70B9");
		IDLabel_4_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4_1_2 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		IDLabel_4_1_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4_1_2_1 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		IDLabel_4_1_2_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel IDLabel_4_1_2_2 = new JLabel("\u6D3B\u52A8\u5185\u5BB9");
		IDLabel_4_1_2_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		EIDtext = new JTextField();
		EIDtext.setEditable(false);
		EIDtext.setColumns(10);
		
		UIDtext = new JTextField();
		UIDtext.setEditable(false);
		UIDtext.setColumns(10);
		
		Nametext = new JTextField();
		Nametext.setColumns(10);
		
		Sponsortext = new JTextField();
		Sponsortext.setColumns(10);
		
		Approvaltext = new JTextField();
		Approvaltext.setColumns(10);
		
		Endtext = new JTextField();
		Endtext.setColumns(10);
		
		Sitetext = new JTextField();
		Sitetext.setColumns(10);
		
		StartTimetext = new JTextField();
		StartTimetext.setColumns(10);
		
		EndTimetext = new JTextField();
		EndTimetext.setColumns(10);
		
		IntroductiontextArea = new JTextArea();
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定修改！")==0) {
					if(EIDtext.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "EID为空");
					}else {
						if(UpdateEvent()) {
							JOptionPane.showMessageDialog(null, "修改成功");
						}else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定删除！")==0) {
					if(EIDtext.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "EID为空");
					}else {
						if(DeleteEvent()) {
							JOptionPane.showMessageDialog(null, "删除成功");
						}else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IDLabel_4_1_2_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IntroductiontextArea, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IDLabel_4_1_2_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(EndTimetext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(IDLabel_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
								.addComponent(IDLabel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(IDLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(UIDLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(EIDLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(IDLabel_4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(IDLabel_4_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(IDLabel_4_1_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(StartTimetext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(Sitetext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(Endtext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(Approvaltext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(Sponsortext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(Nametext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(UIDtext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(EIDtext, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
					.addGap(191))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(111)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(133))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(EIDLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(EIDtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UIDLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(UIDtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(IDLabel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Nametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Sponsortext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Approvaltext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Endtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(Sitetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(StartTimetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4_1_2_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(EndTimetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel_4_1_2_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(IntroductiontextArea, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 初始化显示
	 * @return
	 */
	public Boolean ShowEventAllInformation() {
		if(event == null) {
			return false;
		}
		EIDtext.setText(event.getEventId());
		UIDtext.setText(event.getUserId());
		Nametext.setText(event.getEventName());
		Sponsortext.setText(event.getEventSponsor());
		Approvaltext.setText(Integer.toString(event.getStatusApproval()));
		Endtext.setText(Integer.toString(event.getStatusEnd()));
		Sitetext.setText(eventInformation.getEventSite());
		StartTimetext.setText(eventInformation.getEventStart());
		EndTimetext.setText(eventInformation.getEventEnd());
		IntroductiontextArea.setText(eventInformation.getEventContent());
		return true;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	private Boolean UpdateEvent() {
		Event event = new Event();
		event.setEventId(EIDtext.getText());
		event.setUserId(UIDtext.getText());
		event.setEventName(Nametext.getText());
		event.setEventSponsor(Sponsortext.getText());
		event.setStatusApproval(Integer.valueOf(Approvaltext.getText()));
		event.setStatusEnd(Integer.valueOf(Endtext.getText()));
		EventInformation eventInformation = new EventInformation();
		eventInformation.setEventId(EIDtext.getText());
		eventInformation.setEventSite(Sitetext.getText());
		eventInformation.setEventStart(StartTimetext.getText());
		eventInformation.setEventEnd(EndTimetext.getText());
		eventInformation.setEventContent(IntroductiontextArea.getText());
		EventDao eventDao = new EventDao();
		EventInformationDao eventInformationDao = new EventInformationDao();
		Connection connection = JDBCUtil.getConnection();
		try {
			return eventDao.UpdateEvent(connection, event)==eventInformationDao.UpdateEventInformation(connection, eventInformation)==true;
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
	 * 删除活动
	 * @return
	 */
	private Boolean DeleteEvent() {
		String eID = EIDtext.getText();
		String uID = UIDtext.getText();
		EventDao eventDao = new EventDao();
		EventInformationDao eventInformationDao = new EventInformationDao();
		UserEventDao userEventDao = new UserEventDao();
		Connection connection = JDBCUtil.getConnection();
		try {
			Boolean ED = eventDao.DeleteEvent(connection, eID);
			Boolean EID = eventInformationDao.DeleteEventInformation(connection, eID);
			Boolean UED = userEventDao.UpdateUserEvent(connection, eID, uID);
			return ED==EID==UED==true;
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
