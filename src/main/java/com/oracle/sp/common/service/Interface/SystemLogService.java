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
     * @return a Map�� ���м�ֵΪ data ��ֵΪ���з��������ļ�¼�� ����ֵΪ total ��ֵΪ���������ļ�¼������
     */
    Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException;

    /**
     * ��ҳ��ѯָ���û�ID����¼���ͻ����ڷ�Χ�ĵ���ǳ���¼
     *
     * @param userID       �û�ID
     * @param accessType   ��¼����
     * @param startDateStr ��¼��ʼ����
     * @param endDateStr   ��¼��������
     * @param offset       ��ҳƫ��ֵ
     * @param limit        ��ҳ��С
     * @return ����һ��Map�� ���м�ֵΪ data ��ֵΪ���з��������ļ�¼�� ����ֵΪ total ��ֵΪ���������ļ�¼������
     */
    Map<String, Object> selectAccessRecord(String userName, String accessType, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException;

    /**
     * �����û�������¼
     *
     * @param userID          ִ�в������û�ID
     * @param userName        ִ�в������û���
     * @param operationName   ����������
     * @param operationResult �����ļǹ�
     */
    void insertUserOperationRecord(String userName, String operationName, String operationResult) throws SystemLogServiceException;

    /**
     * ��ѯָ���û�ID�����ڷ�Χ���û�������¼
     *
     * @param userID       �û�ID
     * @param startDateStr ��¼����ʼ����
     * @param endDateStr   ��¼�Ľ�������
     * @return ����һ��Map�� ���м�ֵΪ data ��ֵΪ���з��������ļ�¼�� ����ֵΪ total ��ֵΪ���������ļ�¼������
     */
    Map<String, Object> selectUserOperationRecord(String userName, String startDateStr, String endDateStr) throws SystemLogServiceException;

    /**
     * ��ҳ��ѯָ���û�ID�����ڷ�Χ���û�������¼
     *
     * @param userID       �û�ID
     * @param startDateStr ��¼����ʼ����
     * @param endDateStr   ��¼�Ľ�������
     * @param offset       ��ҳ��ƫ��ֵ
     * @param limit        ��ҳ�Ĵ�С
     * @return ����һ��Map�� ���м�ֵΪ data ��ֵΪ���з��������ļ�¼�� ����ֵΪ total ��ֵΪ���������ļ�¼������
     */
    Map<String, Object> selectUserOperationRecord(String userName, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException;
    
}
