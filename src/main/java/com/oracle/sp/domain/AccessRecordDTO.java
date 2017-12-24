package com.oracle.sp.domain;

public class AccessRecordDTO {

    private Integer id;
    
    private String userName;
    
    private String accessTime;
    
    private String accessIP;
    
    private String accessType;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "AccessRecordDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", accessTime='" + accessTime + '\'' +
                ", accessIP='" + accessIP + '\'' +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
