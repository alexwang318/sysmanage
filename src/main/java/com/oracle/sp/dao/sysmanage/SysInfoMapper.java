package com.oracle.sp.dao.sysmanage;

import java.util.List;

import com.oracle.sp.domain.SysInfoDO;

public interface SysInfoMapper {
	
	SysInfoDO selectBySysID(Integer sysID);
	SysInfoDO selectBySysLabel(String sysLabel);
	List<SysInfoDO> selectAll();
	void update(SysInfoDO sys);
	void deleteByID(Integer id);
	void deleteBySysLabel(String label);
	void insert(SysInfoDO sys);
}
