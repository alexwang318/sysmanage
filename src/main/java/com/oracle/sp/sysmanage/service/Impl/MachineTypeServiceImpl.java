package com.oracle.sp.sysmanage.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.sp.domain.MachineTypeDO;
import com.oracle.sp.exception.MachineTypeServiceException;
import com.oracle.sp.sysmanage.service.Interface.MachineTypeService;

@Service
public class MachineTypeServiceImpl implements MachineTypeService {

	@Override
	public MachineTypeDO getMachineTypeByName(String machineTypeName) throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MachineTypeDO> selectAllTypes() throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMachineType(MachineTypeDO machineTypeDO) throws MachineTypeServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMachineType(MachineTypeDO machineTypeInfoDO) throws MachineTypeServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMachineTypeByName(String machineTypeName) throws MachineTypeServiceException {
		// TODO Auto-generated method stub

	}

}
