package com.oracle.sp.sysmanage.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.sp.domain.MachineTypeDO;
import com.oracle.sp.exception.MachineTypeServiceException;
import com.oracle.sp.sysmanage.service.Interface.MachineTypeService;

@Service
public class MachineTypeServiceImpl implements MachineTypeService {

	@Override
	public MachineTypeDO getByName(String name) throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MachineTypeDO> selectAll() throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(MachineTypeDO machineTypeDO) throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		
		return false;

	}

	@Override
	public boolean update(MachineTypeDO machineTypeDO) throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean deleteByName(String name) throws MachineTypeServiceException {
		// TODO Auto-generated method stub
		
		return false;
	}

}
