package com.oracle.sp.dao.security;

import java.util.List;

import com.oracle.sp.domain.RoleDO;

public interface RolesMapper {
	
	RoleDO getRole(String roleName);
	List<RoleDO> getAllRoles();
}
