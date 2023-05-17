package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qin.entity.UserEvent;

public class UserEventDao {

	/**
	 * 获得用户活动集
	 * @param con
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public UserEvent GetUserEvent(Connection con, String uid) throws SQLException {
		
		UserEvent userEvent = new UserEvent();
		String sql = "select * from user_events where user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, uid);
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			userEvent.setUserId(res.getString(1));
			userEvent.setEventsId(res.getString(2));
		}
		return userEvent;
	}

	/**
	 * 删除某一活动后更新
	 * @param con
	 * @param eid
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Boolean UpdateUserEvent(Connection con,String eid, String uid) throws SQLException{
		String sqlS = "select events_id from user_events where user_id=?";
		String oldIdString = "";
		PreparedStatement pstmtS=con.prepareStatement(sqlS);
		pstmtS.setString(1, uid);
		ResultSet res=pstmtS.executeQuery();
		if(res.next()) {
			oldIdString =  res.getString(1);
		}
		System.out.print(oldIdString);
		List<String> idList = new ArrayList<>(Arrays.asList(oldIdString.split(",")));
		System.out.print(idList);
		idList.remove(idList.indexOf(eid));
		String newIdString = String.join(",", idList);
		String sqlU = "update user_events set events_id=? where user_id=?";
		PreparedStatement pstmtU=con.prepareStatement(sqlU);
		pstmtU.setString(1, newIdString);
		pstmtU.setString(2, uid);
		return pstmtU.executeUpdate()==1;
	}
}
