package com.oracle.sp.dao.sysmanage;


import java.util.List;

import com.oracle.sp.domain.SysInfoDO;
import com.oracle.sp.domain.SysTypeInfoDO;

public interface SysTypeMapper {
	
	/*
	 * Get type info by Name
	 * Such as:
	 * 		typeName: X5-2
	 * can get:
	 * 		typeName: X5-2
	 * 		typeDesc: 1U server which is on Intel grantley platform,
	 * 				  CPU is haswell-ep.
	 */
	SysTypeInfoDO getSysTypeByName(String sysTypeName);
	
	/*
	 * Get all system type info from DB.
	 */
	List<SysTypeInfoDO> selectAllTypes();
	
	/*
	 * Insert a system type record into DB
	 */
	void insertSysType(String sysTypeName, String sysTypeDesc);
	
	/*
	 * Update a system type record into DB
	 */
	void updateSysTypeByID(Integer sysTypeID, String sysTypeName, String sysTypeDesc);
	
	/*
	 * Delete a system type record
	 */
	void deleteSysTypeByName(String sysTypeName);	
}
