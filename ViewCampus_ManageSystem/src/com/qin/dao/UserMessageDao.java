package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qin.entity.User;
import com.qin.entity.UserMessage;

public class UserMessageDao {

	/**
	 * 获得表的全部消息
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<UserMessage> GetMessageList(Connection con) throws SQLException{
		
		List<UserMessage> userMessageList = new ArrayList<>();
		String sql = "select * from user_message";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			UserMessage userMessage = new UserMessage();
			userMessage.setMessageId(res.getString(1));
			userMessage.setUserId(res.getString(2));
			userMessage.setMessage(res.getString(3));
			userMessageList.add(userMessage);
		}
		return userMessageList;
	}
	
	/**
	 * 获取用户的系统消息
	 * @param con
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<UserMessage> GetUserMessageList(Connection con, String uid) throws SQLException{
		
		List<UserMessage> userMessageList = new ArrayList<>();
		String sql = "select * from user_message where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, uid);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			UserMessage userMessage = new UserMessage();
			userMessage.setMessageId(res.getString(1));
			userMessage.setUserId(res.getString(2));
			userMessage.setMessage(res.getString(3));
			userMessageList.add(userMessage);
		}
		return userMessageList;
	}
	
	/**
	 * 添加系统消息
	 * @param con
	 * @param um
	 * @return
	 * @throws SQLException
	 */
	public Boolean SaveSentMessage(Connection con, UserMessage um) throws SQLException{
		String sql = "insert into user_message(message_id, user_id, message) values (?, ?, ?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, um.getMessageId());
		pstmt.setString(2, um.getUserId());
		pstmt.setString(3, um.getMessage());
		return pstmt.executeUpdate()==1;
	}
}
