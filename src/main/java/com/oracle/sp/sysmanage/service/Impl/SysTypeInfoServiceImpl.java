package com.oracle.sp.sysmanage.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.sp.domain.SysTypeInfoDO;
import com.oracle.sp.exception.SysTypeInfoServiceException;
import com.oracle.sp.sysmanage.service.Interface.SysTypeInfoService;

@Service
public class SysTypeInfoServiceImpl implements SysTypeInfoService {

	@Override
	public SysTypeInfoDO getSysTypeByName(String sysTypeName) throws SysTypeInfoServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysTypeInfoDO> selectAllTypes() throws SysTypeInfoServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertSysType(SysTypeInfoDO sysTypeInfoDO) throws SysTypeInfoServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSysTypeInfo(SysTypeInfoDO sysTypeInfoDO) throws SysTypeInfoServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSysTypeByName(String sysTypeName) throws SysTypeInfoServiceException {
		// TODO Auto-generated method stub

	}

}
