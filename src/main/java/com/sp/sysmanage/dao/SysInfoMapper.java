package com.sp.sysmanage.dao;

import java.util.List;

import com.sp.sysmanage.domain.SysInfoDO;

public interface SysInfoMapper {
	
	SysInfoDO selectBySysID(Integer sysID);
	SysInfoDO selectBySysLabel(String sysLabel);
	List<SysInfoDO> selectAll();
	void update(SysInfoDO sys);
	void deleteByID(Integer id);
	void deleteBySysLabel(String label);
	void insert(SysInfoDO sys);
}
