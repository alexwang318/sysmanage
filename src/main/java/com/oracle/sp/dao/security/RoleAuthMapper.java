package com.oracle.sp.dao.security;

import java.util.List;

import com.oracle.sp.domain.RoleAuthDO;

public interface RoleAuthMapper {

	List<RoleAuthDO> selectAll();
}
