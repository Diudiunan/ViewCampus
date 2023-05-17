package com.qin.entity;

public class Admin {
	private String adminId;//管理员名
	private String adminPass;//密码
	
	
	@Override
	public String toString() {
		return "admin [admin_id=" + adminId + ", admin_pass=" + adminPass + "]";
	}
	
	public Admin(String admin_id, String admin_pass) {
		super();
		this.adminId = admin_id;
		this.adminPass = admin_pass;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAdmin_id() {
		return adminId;
	}
	public void setAdmin_id(String admin_id) {
		this.adminId = admin_id;
	}
	public String getAdmin_pass() {
		return adminPass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.adminPass = admin_pass;
	}
	

}
