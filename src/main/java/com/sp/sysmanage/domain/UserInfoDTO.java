package com.sp.sysmanage.domain;

import java.util.ArrayList;
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
	private boolean firstLogin;
	private String accessIP;
	private List<String> role = new ArrayList<>();
	private Integer repositoryBelong;
	
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

    public Integer getRepositoryBelong() {
        return repositoryBelong;
    }

    public void setRepositoryBelong(Integer repositoryBelong) {
        this.repositoryBelong = repositoryBelong;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                ", role=" + role +
                '}';
    }

}
