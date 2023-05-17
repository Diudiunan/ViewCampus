package com.qin.entity;

public class Event {
	private String eventId;
    private String userId;
    private String eventName;
    private String eventSponsor;
    private Integer statusApproval;
    private Integer statusEnd;
    
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(String eventId, String userId, String eventName, String eventSponsor, Integer statusApproval,
			Integer statusEnd) {
		super();
		this.eventId = eventId;
		this.userId = userId;
		this.eventName = eventName;
		this.eventSponsor = eventSponsor;
		this.statusApproval = statusApproval;
		this.statusEnd = statusEnd;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventSponsor() {
		return eventSponsor;
	}

	public void setEventSponsor(String eventSponsor) {
		this.eventSponsor = eventSponsor;
	}

	public Integer getStatusApproval() {
		return statusApproval;
	}

	public void setStatusApproval(Integer statusApproval) {
		this.statusApproval = statusApproval;
	}

	public Integer getStatusEnd() {
		return statusEnd;
	}

	public void setStatusEnd(Integer statusEnd) {
		this.statusEnd = statusEnd;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", userId=" + userId + ", eventName=" + eventName + ", eventSponsor="
				+ eventSponsor + ", statusApproval=" + statusApproval + ", statusEnd=" + statusEnd + "]";
	}
    
	

}
