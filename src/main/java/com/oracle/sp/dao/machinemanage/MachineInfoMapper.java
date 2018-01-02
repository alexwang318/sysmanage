package com.oracle.sp.dao.machinemanage;

import java.util.List;

import com.oracle.sp.domain.MachineInfoDO;

public interface MachineInfoMapper {
	
	MachineInfoDO selectByLabel(String label);
	List<MachineInfoDO> selectAll();
	void update(MachineInfoDO machineInfoDO);
	void deleteByLabel(String label);
	void insert(MachineInfoDO machineInfoDO);
}
