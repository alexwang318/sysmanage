package com.sp.sysmanage.dao;

import com.sp.sysmanage.domain.RoleDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
	
	void insert(@Param("userName") String userName, @Param("roleName") String roleName);
	void deleteByUserID(String userName);
	List<RoleDO> selectUserRole(String userName);

}
