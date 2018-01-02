package com.oracle.sp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * User account information object
 * Used for data transition.
 * 
 * @author binwan
 *
 */
public class UserInfoDTO  implements Serializable{
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String pwd;
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
	private Integer state;

	// The list of role and group names of this user
	private List<String> role = new ArrayList<>();
	private List<String> group = new ArrayList<>();
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
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
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", group=" + group +
                ", firstLogin=" + firstLogin +
                ", lastLoginDate='" + lastLoginDate.toString() + '\'' +
                ", state=" + state +
                ", role=" + role +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
