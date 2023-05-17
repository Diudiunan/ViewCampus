package com.qin.entity;

public class User {

	private String userId;
    private String userAccount;
    private Integer userPass;
    private Integer userRight;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, String userAccount, Integer userPass, Integer userRight) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPass = userPass;
		this.userRight = userRight;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public Integer getUserPass() {
		return userPass;
	}
	public void setUserPass(Integer userPass) {
		this.userPass = userPass;
	}
	public Integer getUserRight() {
		return userRight;
	}
	public void setUserRight(Integer userRight) {
		this.userRight = userRight;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount + ", userPass=" + userPass + ", userRight="
				+ userRight + "]";
	}
    
}
