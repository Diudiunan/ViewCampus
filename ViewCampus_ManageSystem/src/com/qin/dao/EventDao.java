package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qin.entity.Event;

public class EventDao {

	/**
	 * 获得所有活动
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<Event> GetEventList(Connection con) throws SQLException{
		
		List<Event> eventList = new ArrayList<>();
		String sql = "select * from events";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			Event event = new Event();
			event.setEventId(res.getString(1));
			event.setUserId(res.getString(2));
			event.setEventName(res.getString(3));
			event.setEventSponsor(res.getString(4));
			event.setStatusApproval(res.getInt(5));
			event.setStatusEnd(res.getInt(6));
			eventList.add(event);
		}
		return eventList;
	}
	
	/**
	 * 获得活动
	 * @param con
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Event GetEvent(Connection con, String eid) throws SQLException {
		
		Event event = new Event();
		String sql = "select * from events where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, eid);
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			event.setEventId(res.getString(1));
			event.setUserId(res.getString(2));
			event.setEventName(res.getString(3));
			event.setEventSponsor(res.getString(4));
			event.setStatusApproval(res.getInt(5));
			event.setStatusEnd(res.getInt(6));
		}
		return event;
	}
	
	/**
	 * 修改活动
	 * @param con
	 * @param event
	 * @return
	 * @throws SQLException
	 */
	public Boolean UpdateEvent(Connection con, Event event) throws SQLException {
		
		String sql = "update events set event_name=?,event_sponsor=?,status_approval=?,status_end=? where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, event.getEventName());
		pstmt.setString(2, event.getEventSponsor());
		pstmt.setInt(3, event.getStatusApproval());
		pstmt.setInt(4, event.getStatusEnd());
		pstmt.setString(5, event.getEventId());
		return pstmt.executeUpdate()==1;
	}
	
	/**
	 * 删除活动
	 * @param con
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Boolean DeleteEvent(Connection con, String eid) throws SQLException {
		String sql = "delete from events where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, eid);
		return pstmt.executeUpdate()==1;
	}
}
