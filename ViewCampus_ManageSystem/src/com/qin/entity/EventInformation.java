package com.qin.entity;

public class EventInformation {
	
	private String eventId;
    private String eventSite;
    private String eventContent;
    private String eventStart;
    private String eventEnd;
    
    
	public EventInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventInformation(String eventId, String eventSite, String eventContent, String eventStart, String eventEnd) {
		super();
		this.eventId = eventId;
		this.eventSite = eventSite;
		this.eventContent = eventContent;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
	}


	public String getEventId() {
		return eventId;
	}



	public void setEventId(String eventId) {
		this.eventId = eventId;
	}



	public String getEventSite() {
		return eventSite;
	}



	public void setEventSite(String eventSite) {
		this.eventSite = eventSite;
	}



	public String getEventContent() {
		return eventContent;
	}



	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}



	public String getEventStart() {
		return eventStart;
	}



	public void setEventStart(String eventStart) {
		this.eventStart = eventStart;
	}



	public String getEventEnd() {
		return eventEnd;
	}



	public void setEventEnd(String eventEnd) {
		this.eventEnd = eventEnd;
	}



	@Override
	public String toString() {
		return "EventInformation [eventId=" + eventId + ", eventSite=" + eventSite + ", eventContent=" + eventContent
				+ ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + "]";
	}
    

}
