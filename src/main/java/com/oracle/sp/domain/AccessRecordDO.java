package com.oracle.sp.domain;

import java.util.Date;

public class AccessRecordDO {
	
	private Integer record_id;
	private String userName;
	private String accessType;
	private Date accessTime;
	private String accessIP;

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
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
                "id=" + record_id +
                ", userName='" + userName + '\'' +
                ", accessType='" + accessType + '\'' +
                ", accessTime=" + accessTime +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
	
	

}
