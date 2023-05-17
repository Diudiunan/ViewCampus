package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qin.entity.UserInformation;

public class UserInformationDao {
	
	/**
	 * 获得用户信息表
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<UserInformation> GetUserInformationList(Connection con) throws SQLException{
		
		List<UserInformation> userInformationList = new ArrayList<>();
		String sql = "select * from user_informations";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			UserInformation userInformation = new UserInformation();
			userInformation.setUserId(res.getString(1));
			userInformation.setUserName(res.getString(2));
			userInformation.setUserIntroduction(res.getString(3));
			userInformation.setUserUnit(res.getString(4));
			userInformationList.add(userInformation);
		}
		return userInformationList;
	}
	
	/**
	 * 查询用户信息
	 * @param con
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public UserInformation GetUserInformation(Connection con, String uid) throws SQLException {
		
		UserInformation userInformation = new UserInformation();
		String sql = "select * from user_informations where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, uid);
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			userInformation.setUserId(res.getString(1));
			userInformation.setUserName(res.getString(2));
			userInformation.setUserIntroduction(res.getString(3));
			userInformation.setUserUnit(res.getString(4));
		}
		return userInformation;
	}
	
	/**
	 * 更新用户信息
	 * @param con
	 * @param userInformation
	 * @return
	 * @throws SQLException
	 */
	public Boolean UpdateUserInformation(Connection con, UserInformation userInformation) throws SQLException {
		
		String sql = "update user_informations set user_name=?,user_introduction=?,user_unit=? where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userInformation.getUserName());
		pstmt.setString(2, userInformation.getUserIntroduction());
		pstmt.setString(3, userInformation.getUserUnit());
		pstmt.setString(4, userInformation.getUserId());
		return pstmt.executeUpdate()==1;
	}
}
