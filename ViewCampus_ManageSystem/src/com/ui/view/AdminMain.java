package com.ui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
	public AdminMain() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "È·¶¨¹Ø±Õ£¡")==0) {
					System.exit(0);
				}
			}
		});
		
		setTitle("\u89C6\u6821\u56ED\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 717, 567);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu UserMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(UserMenu);
		
		JMenuItem CheckUserMenuItem = new JMenuItem("\u67E5\u770B\u7528\u6237");
		CheckUserMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckUser checkUser = new CheckUser();
				checkUser.setVisible(true);
			}
		});
		UserMenu.add(CheckUserMenuItem);
		
		JMenu EventMenu = new JMenu("\u6D3B\u52A8\u7BA1\u7406");
		menuBar.add(EventMenu);
		
		JMenuItem CheckEventMenuItem = new JMenuItem("\u67E5\u770B\u6D3B\u52A8");
		CheckEventMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckEvent checkEvent = new CheckEvent();
				checkEvent.setVisible(true);
			}
		});
		EventMenu.add(CheckEventMenuItem);
		
		JMenu MessageMenu = new JMenu("\u7CFB\u7EDF\u6D88\u606F\u7BA1\u7406");
		menuBar.add(MessageMenu);
		
		JMenuItem CheckMessageMenuItem = new JMenuItem("\u67E5\u770B\u7CFB\u7EDF\u6D88\u606F");
		CheckMessageMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckMessage checkMessage = new CheckMessage();
				checkMessage.setVisible(true);
			}
		});
		MessageMenu.add(CheckMessageMenuItem);
		
		JMenuItem UpdateMessageMenuItem = new JMenuItem("\u53D1\u9001\u7CFB\u7EDF\u6D88\u606F");
		UpdateMessageMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SentMessage sentMessage = new SentMessage();
				sentMessage.setVisible(true);
			}
		});
		MessageMenu.add(UpdateMessageMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 555, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 430, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
