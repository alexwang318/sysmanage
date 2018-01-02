package com.oracle.sp.domain;

import java.util.Date;

public class UserInfoDO {
	
	private Integer id;
	private String name;
	private String pwd;
	private String email;
	private Integer firstLogin;
	private Date lastLoginDate;
	private Integer state;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Integer firstLogin) {
		this.firstLogin = firstLogin;
	}
	
	@Override
	public String toString() {
		return "UserInfoDO {" +
				"userID=" + id +
				", userName='" + name + '\'' + 
				", email='" + email + '\'' +
				", password='" + pwd + '\'' +
				", fistLogin=" + firstLogin +
				", lastLoginDate='" + lastLoginDate.toString() + '\'' +
				", status=" + state +
				'}';
	}


}
