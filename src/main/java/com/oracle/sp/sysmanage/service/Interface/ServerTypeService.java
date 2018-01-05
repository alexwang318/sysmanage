package com.oracle.sp.sysmanage.service.Interface;

import java.util.List;
import java.util.Map;

import com.oracle.sp.domain.MachineInfoDO;
import com.oracle.sp.domain.ServerTypeDO;
import com.oracle.sp.exception.ServerTypeServiceException;

public interface ServerTypeService {
	
	ServerTypeDO getByName(String name) throws ServerTypeServiceException;
	
	/*
	 * Get all Machinetem type info from DB.
	 */
	Map<String, Object> selectAll() throws ServerTypeServiceException;
	
	Map<String, Object> selectAll(int offset, int limit) throws ServerTypeServiceException;
	
	/*
	 * Insert a Machinetem type record into DB
	 */
	boolean insert(ServerTypeDO machineTypeDO) throws ServerTypeServiceException;
	
	/*
	 * Update a Machinetem type record into DB
	 */
	boolean update(ServerTypeDO machineTypeDO) throws ServerTypeServiceException;
	
	/*
	 * Delete a Machinetem type record
	 */
	boolean deleteByName(String name) throws ServerTypeServiceException;	
}
