package com.oracle.sp.dao.machinemanage;


import java.util.List;

import com.oracle.sp.domain.MachineTypeDO;

public interface MachineTypeMapper {
	
	/*
	 * Get type info by Name
	 * Such as:
	 * 		typeName: X5-2
	 * can get:
	 * 		typeName: X5-2
	 * 		typeDesc: 1U server which is on Intel grantley platform,
	 * 				  CPU is haswell-ep.
	 */
	MachineTypeDO selectByName(String name);
	
	/*
	 * Get all system type info from DB.
	 */
	List<MachineTypeDO> selectAll();
	
	/*
	 * Insert a system type record into DB
	 */
	void insert(MachineTypeDO machineTypeDO);
	
	/*
	 * Update a system type record into DB
	 */
	void update(MachineTypeDO machineTypeDO);
	
	/*
	 * Delete a system type record
	 */
	void deleteByName(String name);	
}
