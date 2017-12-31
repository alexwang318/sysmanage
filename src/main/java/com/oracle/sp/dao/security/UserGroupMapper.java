package com.oracle.sp.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.sp.domain.GroupDO;

public interface UserGroupMapper {
	void insert(@Param("userName")String userName, @Param("groupName")String groupName);
	
	void deleteByUserName(@Param("userName")String userName);
	
	//Get all role names for a user.
	List<GroupDO> selectGroups4User(@Param("userName")String userName);
}
