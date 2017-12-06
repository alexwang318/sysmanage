package com.oracle.sp.sysmanage.service.Interface;

import java.util.List;

import com.oracle.sp.domain.SysInfoDO;
import com.oracle.sp.domain.SysTypeInfoDO;
import com.oracle.sp.exception.SysTypeInfoServiceException;

public interface SysTypeInfoService {
	
	SysTypeInfoDO getSysTypeByName(String sysTypeName) throws SysTypeInfoServiceException;
	
	/*
	 * Get all system type info from DB.
	 */
	List<SysTypeInfoDO> selectAllTypes() throws SysTypeInfoServiceException;
	
	/*
	 * Insert a system type record into DB
	 */
	void insertSysType(SysTypeInfoDO sysTypeInfoDO) throws SysTypeInfoServiceException;
	
	/*
	 * Update a system type record into DB
	 */
	void updateSysTypeInfo(SysTypeInfoDO sysTypeInfoDO) throws SysTypeInfoServiceException;
	
	/*
	 * Delete a system type record
	 */
	void deleteSysTypeByName(String sysTypeName) throws SysTypeInfoServiceException;	
}
