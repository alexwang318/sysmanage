package com.oracle.sp.sysmanage.service.Impl;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.sp.dao.machinemanage.LabLocationMapper;
import com.oracle.sp.domain.LabLocationDO;
import com.oracle.sp.exception.LabLocationServiceException;
import com.oracle.sp.sysmanage.service.Interface.LabLocationService;

@Service
public class LabLocationServiceImpl implements LabLocationService {
	
	@Autowired
	LabLocationMapper labLocationMaper;
	

	@Override
	public LabLocationDO getLocationByName(String name) throws LabLocationServiceException {
		LabLocationDO labLocationDO = new LabLocationDO();
		
		try {
			labLocationDO = labLocationMaper.selectByName(name);
		} catch (PersistenceException e) {
			throw new LabLocationServiceException(e);
		}

		return labLocationDO;
	}

	@Override
	public List<LabLocationDO> getAll(int offset, int limit) throws LabLocationServiceException {
		List<LabLocationDO> labLocationDOS;
		boolean isPagination = true;
		long total = 0;
		
		if (offset < 0 || limit < 0) {
			isPagination = false;
		}
		
		try {
			
			if (isPagination) {
				PageHelper.offsetPage(offset, limit);
			}
			
			labLocationDOS = labLocationMaper.selectAll(); 
			
        	if (isPagination) {
                PageInfo<LabLocationDO> pageInfo = new PageInfo<>(labLocationDOS);
                total = pageInfo.getTotal();
	        } else {
	        	total = labLocationDOS.size();
	        }
			
		} catch (PersistenceException e) {
			throw new LabLocationServiceException(e);
		}
		
		return labLocationDOS;
	}
	
	@Override
	public List<LabLocationDO> getAll() throws LabLocationServiceException {
		return getAll(-1,-1);
	}

	@Override
	public boolean insert(LabLocationDO labLocationDO) throws LabLocationServiceException {
		boolean result = false;
		
		try {
			labLocationMaper.insert(labLocationDO);
			
			// FIXME: need to find out when mybatis return error.
  		    result = true;
			
		} catch (PersistenceException e) {
			throw new LabLocationServiceException(e);
		}
		
		return result;
	}

	@Override
	public boolean update(LabLocationDO labLocationDO) throws LabLocationServiceException {
		boolean result = false;
		
		try {
			labLocationMaper.insert(labLocationDO);
			
			result = true;
		} catch (PersistenceException e) {
			throw new LabLocationServiceException(e);
		}
		
		return result;
	}

	@Override
	public boolean deleteByName(String name) throws LabLocationServiceException {
		boolean result = false;
		
		try {
			labLocationMaper.deleteByName(name);
			
			result = true;
		} catch (PersistenceException e) {
			throw new LabLocationServiceException(e);
		}
		
		return result;
	}

}
