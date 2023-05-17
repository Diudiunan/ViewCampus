package com.qin.entity;

public class UserEvent {
	
	private String userId;
    private String eventsId;
    
	public UserEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEvent(String userId, String eventsId) {
		super();
		this.userId = userId;
		this.eventsId = eventsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEventsId() {
		return eventsId;
	}
	public void setEventsId(String eventsId) {
		this.eventsId = eventsId;
	}
	@Override
	public String toString() {
		return "UserEvent [userId=" + userId + ", eventsId=" + eventsId + "]";
	}
    
    

}
