package com.oracle.sp.sysmanage.service.Interface;

import java.util.List;

import com.oracle.sp.domain.MachineInfoDO;
import com.oracle.sp.domain.MachineTypeDO;
import com.oracle.sp.exception.MachineTypeServiceException;

public interface MachineTypeService {
	
	MachineTypeDO getMachineTypeByName(String MachineTypeName) throws MachineTypeServiceException;
	
	/*
	 * Get all Machinetem type info from DB.
	 */
	List<MachineTypeDO> selectAllTypes() throws MachineTypeServiceException;
	
	/*
	 * Insert a Machinetem type record into DB
	 */
	void insertMachineType(MachineTypeDO MachineTypeInfoDO) throws MachineTypeServiceException;
	
	/*
	 * Update a Machinetem type record into DB
	 */
	void updateMachineType(MachineTypeDO MachineTypeInfoDO) throws MachineTypeServiceException;
	
	/*
	 * Delete a Machinetem type record
	 */
	void deleteMachineTypeByName(String MachineTypeName) throws MachineTypeServiceException;	
}
