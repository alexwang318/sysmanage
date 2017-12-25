package com.oracle.sp.dao.security;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.RoleDO;

import java.util.List;

public interface UserRoleMapper {
	
	void insert(@Param("userName") String userName, @Param("roleName") String roleName);
	
	void deleteByUserName(@Param("userName") String userName);
	
	//Get all role names for a user.
	List<RoleDO> selectRoles4User(@Param("userName") String userName);
	
}
