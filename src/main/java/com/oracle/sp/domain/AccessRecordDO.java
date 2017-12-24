package com.oracle.sp.domain;

import java.util.Date;

public class AccessRecordDO {
	
	private Integer id;
	private String userName;
	private String accessType;
	private Date accessTime;
	private String accessIP;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessIP() {
		return accessIP;
	}

	public void setAccessIP(String accessIP) {
		this.accessIP = accessIP;
	}
	
    @Override
    public String toString() {
        return "AccessRecordDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", accessType='" + accessType + '\'' +
                ", accessTime=" + accessTime +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
