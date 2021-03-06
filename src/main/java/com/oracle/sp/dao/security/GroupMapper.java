package com.oracle.sp.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.GroupDO;


public interface GroupMapper {
	GroupDO getGroup(@Param("name") String name);
	List<GroupDO> getAllGroups();
	void insert(@Param("groupDO") GroupDO groupDO);
}
