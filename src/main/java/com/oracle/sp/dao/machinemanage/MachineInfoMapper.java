package com.oracle.sp.dao.machinemanage;

import java.util.List;

import com.oracle.sp.domain.MachineInfoDO;

public interface MachineInfoMapper {
	
	MachineInfoDO selectByLabel(String sysLabel);
	List<MachineInfoDO> selectAll();
	void update(MachineInfoDO sys);
	void deleteByLabel(String sysLabel);
	void insert(MachineInfoDO sys);
}
