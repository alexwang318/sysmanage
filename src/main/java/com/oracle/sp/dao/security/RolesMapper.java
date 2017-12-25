package com.oracle.sp.dao.security;

import com.oracle.sp.domain.RoleDO;

public interface RolesMapper {
	
	RoleDO getRole(String roleName);
}
