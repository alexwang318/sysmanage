package com.oracle.sp.dao.machinemanage;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.ServerTypeDO;

public interface ServerTypeMapper {
	
	/*
	 * Get type info by Name
	 * Such as:
	 * 		typeName: X5-2
	 * can get:
	 * 		typeName: X5-2
	 * 		typeDesc: 1U server which is on Intel grantley platform,
	 * 				  CPU is haswell-ep.
	 */
	ServerTypeDO selectByName(String name);
	
	/*
	 * Get all system type info from DB.
	 */
	List<ServerTypeDO> selectAll();
	
	/*
	 * Insert a system type record into DB
	 */
	void insert(@Param("serverTypeDO")ServerTypeDO serverTypeDO);
	
	/*
	 * Update a system type record into DB
	 */
	void update(ServerTypeDO serverTypeDO);
	
	/*
	 * Delete a system type record
	 */
	void deleteByName(String name);	
}
