package com.oracle.sp.common.service.Impl;

import com.oracle.sp.common.service.Interface.SystemLogService;
import com.oracle.sp.dao.syslogs.AccessRecordMapper;
import com.oracle.sp.domain.AccessRecordDO;
import com.oracle.sp.exception.SystemLogServiceException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;



@Service
public class SystemLogServiceImpl implements SystemLogService {
	
	@Autowired
	private AccessRecordMapper accessRecordMapper;
	
    @Override
    public void insertAccessRecord(String userName, 
    		String accessIP, 
    		String accessType) throws SystemLogServiceException {
    	
    	AccessRecordDO accessRecordDO = new AccessRecordDO();
    	accessRecordDO.setUserName(userName);
    	accessRecordDO.setAccessIP(accessIP);
    	accessRecordDO.setAccessTime(new Date());
    	accessRecordDO.setAccessType(accessType);
    	
    	try {
    		accessRecordMapper.insertAccessRecord(accessRecordDO);
    	} catch (PersistenceException e) {
    		throw new SystemLogServiceException(e, "Fail to persist AccessRecordDO Object");
    	}
    
    }
    
    @Override
    public Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException {
        return selectAccessRecord(userName, accessType, startDateStr, endDateStr, -1, -1);
    }
    
    @Override
    public Map<String, Object> selectAccessRecord(String userName, 
    		String accessType, 
    		String startDateStr, 
    		String endDateStr, 
    		int offset, 
    		int limit) throws SystemLogServiceException {
    	
    	Map<String, Object> result = new HashMap<>();
    	
    	//FIX ME LATER.
    	result.put("data", "alex test");
    	
    	return result;
    }
    
    @Override
    public void insertUserOperationRecord(String userName, 
    		String operationName, 
    		String operationResult) throws SystemLogServiceException {
    	
    }
         
    @Override
    public Map<String, Object> selectUserOperationRecord(String userName, 
    		String startDateStr, 
    		String endDateStr) throws SystemLogServiceException {
        return selectUserOperationRecord(userName, startDateStr, endDateStr, -1, -1);
    }
    
    @Override
    public Map<String, Object> selectUserOperationRecord(String userName, 
    		String startDateStr, 
    		String endDateStr, 
    		int offset, 
    		int limit) throws SystemLogServiceException {
    	
    	Map<String, Object> result = new HashMap<>();
    	
    	//FIX ME LATER.
    	result.put("data", "alex test");
    	
    	return result;
    }
    
}
