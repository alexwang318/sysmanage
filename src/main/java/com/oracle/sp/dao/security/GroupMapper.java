package com.oracle.sp.dao.security;

import java.util.List;

import com.oracle.sp.domain.GroupDO;


public interface GroupMapper {
	GroupDO getGroup(String groupName);
	List<GroupDO> getAllGroups();
}
