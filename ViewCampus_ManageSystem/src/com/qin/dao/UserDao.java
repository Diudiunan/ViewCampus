package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qin.entity.User;

public class UserDao {

	/**
	 * 获得用户表
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<User> GetUserList(Connection con) throws SQLException{
		
		List<User> userList = new ArrayList<>();
		String sql = "select * from users";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			User user = new User();
			user.setUserId(res.getString(1));
			user.setUserAccount(res.getString(2));
			user.setUserPass(res.getInt(3));
			user.setUserRight(res.getInt(4));
			userList.add(user);
		}
		return userList;
	}
	
	/**
	 * 查询用户
	 * @param con
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public User GetUserInformation(Connection con, String uid) throws SQLException {
		
		User user = new User();
		String sql = "select * from users where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, uid);
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			user.setUserId(res.getString(1));
			user.setUserAccount(res.getString(2));
			user.setUserPass(res.getInt(3));
			user.setUserRight(res.getInt(4));
		}
		return user;
	}
	
	/**
	 * 更新用户
	 * @param con
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Boolean UpdateUser(Connection con, User user) throws SQLException {
		
		String sql = "update users set user_account=?,user_pass=?,user_right=? where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserAccount());
		pstmt.setInt(2, user.getUserPass());
		pstmt.setInt(3, user.getUserRight());
		pstmt.setString(4, user.getUserId());
		return pstmt.executeUpdate()==1;
	}
}
