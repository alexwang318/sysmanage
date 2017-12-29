package com.oracle.sp.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.GroupDO;

public interface UserGroupMapper {
	void insert(String userName, String groupName);
	
	void deleteByUserName(String userName);
	
	//Get all role names for a user.
	List<GroupDO> selectGroups4User(String userName);
}
