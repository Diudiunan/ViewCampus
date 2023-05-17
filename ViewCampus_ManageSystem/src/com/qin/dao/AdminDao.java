package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qin.entity.Admin;

public class AdminDao {
	
	/**
	 * π‹¿Ì‘±µ«¬º
	 * @param con
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public Boolean AdminLogIn(Connection con, Admin admin) throws SQLException {
		Admin reAdmin = new Admin();
		String mysql="SELECT * FROM admin where admin_id=? and admin_pass=?";
		PreparedStatement pstmt=con.prepareStatement(mysql);
		pstmt.setString(1, admin.getAdmin_id());
		pstmt.setString(2, admin.getAdmin_pass());
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			return true;
		}else {
			return false;
		}
	}
}
