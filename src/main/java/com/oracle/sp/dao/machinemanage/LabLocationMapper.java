package com.oracle.sp.dao.machinemanage;

import java.util.List;

import com.oracle.sp.domain.LabLocationDO;

public interface LabLocationMapper {
	LabLocationDO selectByName(String name);
	
	/*
	 * Get all system type info from DB.
	 */
	List<LabLocationDO> selectAll();
	
	/*
	 * Insert a system type record into DB
	 */
	void insert(LabLocationDO machineTypeDO);
	
	/*
	 * Update a system type record into DB
	 */
	void update(LabLocationDO machineTypeDO);
	
	/*
	 * Delete a system type record
	 */
	void deleteByName(String name);	
}
