package com.oracle.sp.common.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.oracle.sp.common.service.Interface.SystemLogService;
import com.oracle.sp.dao.syslogs.AccessRecordMapper;
import com.oracle.sp.dao.syslogs.UserOperationRecordMapper;
import com.oracle.sp.domain.AccessRecordDO;
import com.oracle.sp.domain.AccessRecordDTO;
import com.oracle.sp.domain.UserOperationRecordDO;
import com.oracle.sp.domain.UserOperationRecordDTO;
import com.oracle.sp.exception.SystemLogServiceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SystemLogServiceImpl implements SystemLogService {
	
    @Autowired
    private AccessRecordMapper accessRecordMapper;
    @Autowired
    private UserOperationRecordMapper userOperationRecordMapper;
	
    private DateFormat dateFormatDetail = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private DateFormat dateFormatSimple = new SimpleDateFormat("yyyy-MM-dd");
    
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
    	
        Map<String, Object> resultSet = new HashMap<>();
        List<AccessRecordDTO> accessRecordDTOS = new ArrayList<>();
        long total = 0;
        boolean isPagination = true;

        // see whether we need to paginate
        if (offset < 0 || limit < 0)
            isPagination = false;

        Date startDate = null;
        Date endDate = null;
        try {
            if (StringUtils.isNotEmpty(startDateStr))
                startDate = dateFormatSimple.parse(startDateStr);
            if (StringUtils.isNotEmpty(endDateStr)) {
                endDate = dateFormatSimple.parse(endDateStr);
                endDate = DateUtils.addDays(endDate, 1);
            }

        } catch (ParseException e) {
            throw new SystemLogServiceException(e, "Fail to convert string to Date Object");
        }

        switch (accessType) {
            case "loginOnly":
                accessType = SystemLogService.ACCESS_LOGIN;
                break;
            case "logoutOnly":
                accessType = SystemLogService.ACCESS_LOGOUT;
                break;
            case "changePassword":
                accessType = SystemLogService.ACCESS_CHANGE_PASSWORD;
                break;
            default:
                accessType = "all";
                break;
        }

        // Define AccessRecordDO stream
        List<AccessRecordDO> accessRecordDOS;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                accessRecordDOS = accessRecordMapper.selectAccessRecord(userName, accessType, startDate, endDate);
                if (accessRecordDOS != null) {
                    accessRecordDOS.forEach(accessRecordDO -> accessRecordDTOS.add(convertAccessRecordDOToAccessRecordDTO(accessRecordDO)));
                    total = new PageInfo<>(accessRecordDOS).getTotal();
                }
            } else {
                accessRecordDOS = accessRecordMapper.selectAccessRecord(userName, accessType, startDate, endDate);
                if (accessRecordDOS != null) {
                    accessRecordDOS.forEach(accessRecordDO -> accessRecordDTOS.add(convertAccessRecordDOToAccessRecordDTO(accessRecordDO)));
                    total = accessRecordDOS.size();
                }
            }
        } catch (PersistenceException e) {
            throw new SystemLogServiceException(e);
        }

        resultSet.put("data", accessRecordDTOS);
        resultSet.put("total", total);
        return resultSet;
    }
    
    @Override
    public void insertUserOperationRecord(String userName, 
    		String operationName, 
    		String operationResult) 
    				throws SystemLogServiceException {

        UserOperationRecordDO userOperationRecordDO = new UserOperationRecordDO();
        userOperationRecordDO.setUserName(userName);
        userOperationRecordDO.setOperationName(operationName);
        userOperationRecordDO.setOperationResult(operationResult);
        userOperationRecordDO.setOperationTime(new Date());

        try {
            userOperationRecordMapper.insertUserOperationRecord(userOperationRecordDO);
        } catch (PersistenceException e) {
            throw new SystemLogServiceException(e, "Fail to persist usrOperationRecordDo Object");
        }
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

        Map<String, Object> resultSet = new HashMap<>();
        List<UserOperationRecordDTO> userOperationRecordDTOS = new ArrayList<>();
        long total = 0;
        boolean isPagination = true;

        // Do we need to do pagination?
        if (offset < 0 && limit < 0)
            isPagination = false;

        Date startDate = null;
        Date endDate = null;
        try {
            if (StringUtils.isNotEmpty(startDateStr))
                startDate = dateFormatSimple.parse(startDateStr);
            if (StringUtils.isNotEmpty(endDateStr)) {
                endDate = dateFormatSimple.parse(endDateStr);
                endDate = DateUtils.addDays(endDate, 1);
            }
        } catch (ParseException e) {
            throw new SystemLogServiceException(e, "Fail to convert String format date to Date Object");
        }

        List<UserOperationRecordDO> userOperationRecordDOS;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                userOperationRecordDOS = userOperationRecordMapper.selectUserOperationRecord(userName, startDate, endDate);
                if (userOperationRecordDOS != null) {
                    userOperationRecordDOS.forEach(userOperationRecordDO -> userOperationRecordDTOS.add(convertUserOperationRecordDOToUserOperationRecordDTO(userOperationRecordDO)));
                    total = new PageInfo<>(userOperationRecordDOS).getTotal();
                }
            } else {
                userOperationRecordDOS = userOperationRecordMapper.selectUserOperationRecord(userName, startDate, endDate);
                if (userOperationRecordDOS != null)
                    userOperationRecordDOS.forEach(userOperationRecordDO -> userOperationRecordDTOS.add(convertUserOperationRecordDOToUserOperationRecordDTO(userOperationRecordDO)));
            }
        } catch (PersistenceException e) {
            throw new SystemLogServiceException(e);
        }

        resultSet.put("data", userOperationRecordDTOS);
        resultSet.put("total", total);
        return resultSet;
    }
    
    /*
     * The following two functions' main jobs are convert date to string.
     */
    private AccessRecordDTO convertAccessRecordDOToAccessRecordDTO(AccessRecordDO accessRecordDO) {
        AccessRecordDTO accessRecordDTO = new AccessRecordDTO();
        accessRecordDTO.setId(accessRecordDO.getId());
        accessRecordDTO.setUserName(accessRecordDO.getUserName());
        accessRecordDTO.setAccessIP(accessRecordDO.getAccessIP());
        accessRecordDTO.setAccessType(accessRecordDO.getAccessType().equals(SystemLogService.ACCESS_LOGIN) ? "login" : "logout");
        accessRecordDTO.setAccessTime(dateFormatDetail.format(accessRecordDO.getAccessTime()));
        return accessRecordDTO;
    }
    
    private UserOperationRecordDTO convertUserOperationRecordDOToUserOperationRecordDTO(UserOperationRecordDO userOperationRecordDO) {
        UserOperationRecordDTO userOperationRecordDTO = new UserOperationRecordDTO();
        userOperationRecordDTO.setId(userOperationRecordDO.getId());
        userOperationRecordDTO.setUserName(userOperationRecordDO.getUserName());
        userOperationRecordDTO.setOperationName(userOperationRecordDO.getOperationName());
        userOperationRecordDTO.setOperationResult(userOperationRecordDO.getOperationResult());
        userOperationRecordDTO.setOperationTime(dateFormatDetail.format(userOperationRecordDO.getOperationTime()));
        return userOperationRecordDTO;
    }
}
