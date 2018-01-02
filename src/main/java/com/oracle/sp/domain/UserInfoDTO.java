package com.oracle.sp.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * User account information object
 * Used for data transition.
 * 
 * @author binwan
 *
 */
public class UserInfoDTO{
	
	private Integer userID;
	private String userName;
	private String password;
	private String email;

	// To indicate whether this is the first login of this account. If it's, we can ask user
	// to do some info update.
	private boolean firstLogin;

	// Access IP of current session.
	private String accessIP;

	// Last login date of this user. It's used to check whether this user account is time out
	// according to timeout policy setting by Admin.
	private String lastLoginDate;

	// User account status, 1: enabled, 0: disabled.
	private Integer status;

	// The list of role and group names of this user
	private List<String> role = new ArrayList<>();
	private List<String> group = new ArrayList<>();
	
    public String getUserName() {
        return userName;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<String> getRole() {
        return role;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getGroup() {
		return group;
	}

	public void setGroup(List<String> group) {
		this.group = group;
	}
	
    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group.toString() + '\'' +
                ", firstLogin=" + firstLogin +
                ", lastLoginDate='" + lastLoginDate.toString() + '\'' +
                ", status=" + status +
                ", role=" + role.toString() +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
