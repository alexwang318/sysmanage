package com.oracle.sp.sysmanage.service.Interface;

import java.util.List;

import com.oracle.sp.domain.MachineInfoDO;
import com.oracle.sp.domain.MachineTypeDO;
import com.oracle.sp.exception.MachineTypeServiceException;

public interface MachineTypeService {
	
	MachineTypeDO getByName(String name) throws MachineTypeServiceException;
	
	/*
	 * Get all Machinetem type info from DB.
	 */
	List<MachineTypeDO> selectAll() throws MachineTypeServiceException;
	
	/*
	 * Insert a Machinetem type record into DB
	 */
	boolean insert(MachineTypeDO machineTypeDO) throws MachineTypeServiceException;
	
	/*
	 * Update a Machinetem type record into DB
	 */
	boolean update(MachineTypeDO machineTypeDO) throws MachineTypeServiceException;
	
	/*
	 * Delete a Machinetem type record
	 */
	boolean deleteByName(String name) throws MachineTypeServiceException;	
}
