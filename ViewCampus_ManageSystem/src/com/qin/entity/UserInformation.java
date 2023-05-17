package com.qin.entity;

public class UserInformation {
	
	private String userId;
    private String userName;
    private String userIntroduction;
    private String userUnit;
	public UserInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInformation(String userId, String userName, String userIntroduction, String userUnit) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userIntroduction = userIntroduction;
		this.userUnit = userUnit;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIntroduction() {
		return userIntroduction;
	}
	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}
	public String getUserUnit() {
		return userUnit;
	}
	public void setUserUnit(String userUnit) {
		this.userUnit = userUnit;
	}
	@Override
	public String toString() {
		return "UserInformation [userId=" + userId + ", userName=" + userName + ", userIntroduction=" + userIntroduction
				+ ", userUnit=" + userUnit + "]";
	}
    
    

}
