package com.oracle.sp.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.RoleDO;

public interface RolesMapper {
	
	RoleDO getRole(@Param("roleName")String roleName);
	List<RoleDO> getAllRoles();
}
