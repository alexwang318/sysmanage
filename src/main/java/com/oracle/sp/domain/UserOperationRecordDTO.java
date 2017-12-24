package com.oracle.sp.domain;

/*
 * This class is for Data transfer.
 * The only difference with 'UserOperrationRecordDO' is the format of 'time'.
 * In this class, we use string for data transfer, but in 'DO', its data is from
 * Database, so the format of 'time' is Date.
 * 
 * Usually, we will need to provide the convert function between these two objects.
 * 
 * In service/view level, we use 'DTO' for data transfer.
 * In DAO level, we use 'DO' to transfer data.
 */

public class UserOperationRecordDTO {
    private Integer id;

    private String userName;

    private String operationName;

    private String operationTime;

    private String operationResult;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getOperationName() {
        return operationName;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    @Override
    public String toString() {
        return "UserOperationRecordDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", operationName='" + operationName + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", operationResult='" + operationResult + '\'' +
                '}';
    }
}
