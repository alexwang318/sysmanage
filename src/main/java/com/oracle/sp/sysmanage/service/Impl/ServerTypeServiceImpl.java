package com.oracle.sp.sysmanage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.sp.common.controller.machineManageHandler;
import com.oracle.sp.dao.machinemanage.ServerTypeMapper;
import com.oracle.sp.domain.LabLocationDO;
import com.oracle.sp.domain.ServerTypeDO;
import com.oracle.sp.exception.LabLocationServiceException;
import com.oracle.sp.exception.ServerTypeServiceException;
import com.oracle.sp.sysmanage.service.Interface.ServerTypeService;

@Service
public class ServerTypeServiceImpl implements ServerTypeService {
	
	@Autowired
	ServerTypeMapper serverTypeMapper;
	
	
	private static Logger log = Logger.getLogger(ServerTypeServiceImpl.class);
	

	@Override
	public ServerTypeDO getByName(String name) throws ServerTypeServiceException {
		// TODO Auto-generated method stub
		ServerTypeDO serverTypeDO = new ServerTypeDO();
		
		try {
			serverTypeDO = serverTypeMapper.selectByName(name);
		} catch (PersistenceException e) {
			throw new ServerTypeServiceException(e);
		}

		return serverTypeDO;

	}

	@Override
	public Map<String, Object> selectAll() throws ServerTypeServiceException {
		return selectAll(-1, -1);
	}
	
	@Override
	public Map<String, Object> selectAll(int offset, int limit) throws ServerTypeServiceException {
		Map<String, Object> resultSet = new HashMap<>();
		List<ServerTypeDO> serverTypeDOS;
		boolean isPagination = true;
		long total = 0;
		
		if (offset < 0 || limit < 0) {
			isPagination = false;
		}
		
		try {
			
			if (isPagination) {
				PageHelper.offsetPage(offset, limit);
			}
			
			serverTypeDOS = serverTypeMapper.selectAll(); 
			
        	if (isPagination) {
                PageInfo<ServerTypeDO> pageInfo = new PageInfo<>(serverTypeDOS);
                total = pageInfo.getTotal();
	        } else {
	        	total = serverTypeDOS.size();
	        }
			
		} catch (PersistenceException e) {
			throw new ServerTypeServiceException(e);
		}
		
        resultSet.put("data", serverTypeDOS);
        resultSet.put("total", total);
		
        return resultSet;
	}

	@Override
	public boolean insert(ServerTypeDO serverTypeDOS) throws ServerTypeServiceException {
		boolean result = false;
		
		try {
			
			log.error(serverTypeDOS.toString());
			
			serverTypeMapper.insert(serverTypeDOS);
			
			// FIXME: need to find out when mybatis return error.
  		    result = true;
			
		} catch (PersistenceException e) {
			throw new ServerTypeServiceException(e);
		}
		
		return result;

	}

	@Override
	public boolean update(ServerTypeDO serverTypeDOS) throws ServerTypeServiceException {
		boolean result = false;
		
		try {
			serverTypeMapper.update(serverTypeDOS);
			
			// FIXME: need to find out when mybatis return error.
  		    result = true;
			
		} catch (PersistenceException e) {
			throw new ServerTypeServiceException(e);
		}
		
		return result;
	}

	@Override
	public boolean deleteByName(String name) throws ServerTypeServiceException {
		boolean result = false;
		
		try {
			serverTypeMapper.deleteByName(name);
			
			// FIXME: need to find out when mybatis return error.
  		    result = true;
			
		} catch (PersistenceException e) {
			throw new ServerTypeServiceException(e);
		}
		
		return result;
	}

}
