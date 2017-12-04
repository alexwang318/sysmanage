package com.sp.sysmanage.domain;

import java.util.Date;

public class UserInfoDO {
	
	private Integer userID;
	private String userName;
	private String password;
	private Integer firstLogin;
	private Date lastLoginDate;
	private Integer status;
	
	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getFirstLogin() {
		return firstLogin;
	}
	
	public void setFirstLogin(Integer firstLogin) {
		this.firstLogin = firstLogin;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	
	
	// FIXME later!!!!
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	@Override
	public String toString() {
		return "UserInfoDO {" +
				"userID=" + userID +
				", userName='" + userName + '\'' + 
				", password='" + password + '\'' +
				", fistLogin=" + firstLogin +
				", lastLoginDate='" + lastLoginDate.toString() + '\'' +
				", status=" + status +
				'}';
	}
	
}
