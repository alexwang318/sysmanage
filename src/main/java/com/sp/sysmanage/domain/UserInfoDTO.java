package com.sp.sysmanage.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * User account information object
 * Used for data transition.
 * 
 * @author binwan
 *
 */
public class UserInfoDTO {
	
	private Integer userID;
	private String userName;
	private String password;

	// To indicate whether this is the first login of this acount. If it's, we can ask user
	// to do some info update.
	private boolean firstLogin;

	// Access IP of current session.
	private String accessIP;

	// Last login date of this user. It's used to check whether this user account is time out
	// according to timeout policy setting by Admin.
	private Date lastLoginDate;

	// User account status, 1: active, 0: deactive.
	private Integer status;

	// The list of role names of this user
	private List<String> role = new ArrayList<>();
	
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

    public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                ", lastLoginDate='" + lastLoginDate.toString() + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
