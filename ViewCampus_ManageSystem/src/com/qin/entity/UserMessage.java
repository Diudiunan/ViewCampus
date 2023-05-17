package com.qin.entity;

public class UserMessage {
	private String messageId;
    private String userId;
    private String message;
	public UserMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMessage(String messageId, String userId, String message) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "UserMessage [messageId=" + messageId + ", userId=" + userId + ", message=" + message + "]";
	}
    

}
