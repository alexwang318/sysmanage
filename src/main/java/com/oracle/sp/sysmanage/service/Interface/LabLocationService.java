package com.oracle.sp.sysmanage.service.Interface;

import java.util.List;
import java.util.Map;

import com.oracle.sp.domain.LabLocationDO;
import com.oracle.sp.exception.LabLocationServiceException;

public interface LabLocationService {
	LabLocationDO getLocationByName(String name) throws LabLocationServiceException;
	
	Map<String, Object> getAll() throws LabLocationServiceException;
	
	//This function is used for pagination.
	Map<String, Object> getAll(int offset, int limit) throws LabLocationServiceException;
	
	boolean insert(LabLocationDO labLocationDO) throws LabLocationServiceException;
	
	boolean update(LabLocationDO labLocationDO) throws LabLocationServiceException;
	
	boolean deleteByName(String name) throws LabLocationServiceException;
}
