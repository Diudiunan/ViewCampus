package com.qin.jdbc;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
	private static String driver_p;
	private static String username_p;
	private static String password_p;
	private static String url_p;
	
	//��̬����
	static {
		//JDBCUtils.class��ö���
		//getClassLoader()�������
		//getResourceAsStream("rs.properties")������Դ�ļ��ŵ���������
		InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("rs.properties");
		
		//����Properties���͵Ķ���
		Properties p = new Properties();
		
		//�������ļ�
		try {
			p.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		driver_p = p.getProperty("driver");
		username_p = p.getProperty("mysql_username");
		password_p = p.getProperty("mysql_password");
		url_p = p.getProperty("mysql_url");
		
		try {
			//����MySQL����
			Class.forName(driver_p);
			System.out.println("�������سɹ�");
			//System.out.println(url_p+username_p+password_p);//��������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//������Ӷ���
	//�������ݿ�
	public static Connection getConnection() {
		Connection connection_mq = null;
		try {
			connection_mq = DriverManager.getConnection(url_p, username_p, password_p);
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�鿴���ݿ�����");
		}
		return connection_mq;
	}
	
	//�ͷ���Դ�ķ���
	public static void close_rele_all(Connection connection, Statement statement,ResultSet result){
		try {
			if(connection!=null) {
				connection.close();
				connection = null;
			}
			if(statement!=null) {
				statement.close();
				statement = null;
			}
			if(result!=null) {
				result.close();
				result = null;
			}
			System.out.println("�ͷ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
