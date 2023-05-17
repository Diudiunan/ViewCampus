package com.test;

import java.sql.Connection;

import org.junit.Test;

import com.qin.dao.AdminDao;
import com.qin.entity.Admin;
import com.qin.jdbc.JDBCUtil;

public class AdminTest {
	
	//private AdminDao admindao;

	@Test
	public void AdminLogIn() throws Exception {
		AdminDao admindao = new AdminDao();
		Connection con = JDBCUtil.getConnection();
		Admin admin = new Admin("diudiunan","Qin");
		System.out.println(admindao.AdminLogIn(con, admin));
	}
}
