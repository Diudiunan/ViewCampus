package com.qin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qin.entity.EventInformation;

public class EventInformationDao {

	/**
	 * 获得活动信息
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<EventInformation> GetEventList(Connection con) throws SQLException{
		
		List<EventInformation> eventInformationList = new ArrayList<>();
		String sql = "select * from event_informations";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			EventInformation event = new EventInformation();
			event.setEventId(res.getString(1));
			event.setEventSite(res.getString(2));
			event.setEventContent(res.getString(3));
			event.setEventStart(res.getString(4));
			event.setEventEnd(res.getString(5));
			eventInformationList.add(event);
		}
		return eventInformationList;
	}
	
	/**
	 * 获得活动信息
	 * @param con
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public EventInformation GetEventInformation(Connection con, String eid) throws SQLException {
		
		EventInformation event = new EventInformation();
		String sql = "select * from event_informations where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, eid);
		ResultSet res=pstmt.executeQuery();
		if(res.next()) {
			event.setEventId(res.getString(1));
			event.setEventSite(res.getString(2));
			event.setEventContent(res.getString(3));
			event.setEventStart(res.getString(4));
			event.setEventEnd(res.getString(5));
		}
		return event;
	}
	
	/**
	 * 修改活动信息
	 * @param con
	 * @param eventInformation
	 * @return
	 * @throws SQLException
	 */
	public Boolean UpdateEventInformation(Connection con, EventInformation eventInformation) throws SQLException {
		
		String sql = "update event_informations set event_site=?,event_content=?,event_start=?,event_end=? where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, eventInformation.getEventSite());
		pstmt.setString(2, eventInformation.getEventContent());
		pstmt.setString(3, eventInformation.getEventStart());
		pstmt.setString(4, eventInformation.getEventEnd());
		pstmt.setString(5, eventInformation.getEventId());
		return pstmt.executeUpdate()==1;
	}
	
	/**
	 * 删除活动信息
	 * @param con
	 * @param eid
	 * @return
	 * @throws SQLException
	 */
	public Boolean DeleteEventInformation(Connection con, String eid) throws SQLException {
		String sql = "delete from event_informations where event_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, eid);
		return pstmt.executeUpdate()==1;
	}
}
