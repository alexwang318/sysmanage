package com.oracle.sp.common.service.Interface;

import java.util.Map;

import com.oracle.sp.exception.SystemLogServiceException;

public interface SystemLogService {
	
	String ACCESS_LOGIN = "login";
	String ACCESS_LOGOUT = "logout";
	String ACCESS_CHANGE_PASSWORD = "changePassword";
	
	void insertAccessRecord(String userName, 
			String accessIP, 
			String accessType) throws SystemLogServiceException;
	
    Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException;

    Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr, 
    		int offset, 
    		int limit) throws SystemLogServiceException;

    void insertUserOperationRecord(String userName, 
            String operationName, 
            String operationResult) throws SystemLogServiceException;

    Map<String, Object> selectUserOperationRecord(String userName, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException;

    Map<String, Object> selectUserOperationRecord(String userName, 
    		String startDateStr, 
    		String endDateStr, 
    		int offset, 
    		int limit) throws SystemLogServiceException;
    
}
