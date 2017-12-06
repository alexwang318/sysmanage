package com.oracle.sp.common.service.Interface;

import java.util.Map;

import com.oracle.sp.exception.SystemLogServiceException;

public interface SystemLogService {
	
	String ACCESS_LOGIN = "login";
	String ACCESS_LOGOUT = "logout";
	
	/**
	 * Insert login/logout record
	 * 
	 * @param userID
	 * @param userName
	 * @param accessIP
	 * @param accessType
	 */
	void insertAccessRecord(String userName, 
			String accessIP, 
			String accessType) throws SystemLogServiceException;
	
    /**
     * Query the login record by user id, type, startData and endData
     *
     * @param userID       
     * @param accessType  
     * @param startDateStr 
     * @param endDateStr   
     * @return a Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException;

    /**
     * 分页查询指定用户ID、记录类型或日期范围的登入登出记录
     *
     * @param userID       用户ID
     * @param accessType   记录类型
     * @param startDateStr 记录起始日期
     * @param endDateStr   记录结束日期
     * @param offset       分页偏移值
     * @param limit        分页大小
     * @return 返回一个Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    Map<String, Object> selectAccessRecord(String userName, String accessType, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException;

    /**
     * 插入用户操作记录
     *
     * @param userID          执行操作的用户ID
     * @param userName        执行操作的用户名
     * @param operationName   操作的名称
     * @param operationResult 操作的记过
     */
    void insertUserOperationRecord(String userName, String operationName, String operationResult) throws SystemLogServiceException;

    /**
     * 查询指定用户ID或日期范围的用户操作记录
     *
     * @param userID       用户ID
     * @param startDateStr 记录的起始日期
     * @param endDateStr   记录的结束日期
     * @return 返回一个Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    Map<String, Object> selectUserOperationRecord(String userName, String startDateStr, String endDateStr) throws SystemLogServiceException;

    /**
     * 分页查询指定用户ID或日期范围的用户操作记录
     *
     * @param userID       用户ID
     * @param startDateStr 记录的起始日期
     * @param endDateStr   记录的结束日期
     * @param offset       分页的偏移值
     * @param limit        分页的大小
     * @return 返回一个Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    Map<String, Object> selectUserOperationRecord(String userName, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException;
    
}
