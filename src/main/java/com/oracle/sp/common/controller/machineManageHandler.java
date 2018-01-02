package com.oracle.sp.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.sp.common.util.Response;
import com.oracle.sp.common.util.ResponseFactory;
import com.oracle.sp.dao.security.GroupMapper;
import com.oracle.sp.dao.security.RolesMapper;
import com.oracle.sp.domain.MachineInfoDTO;
import com.oracle.sp.exception.MachineManageServiceException;
import com.oracle.sp.security.service.Interface.UserInfoService;

@RequestMapping(value="/machineManage")
@Controller
public class machineManageHandler {
	@Autowired
	private UserInfoService userInfoService;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private GroupMapper groupMapper;
    
	@RequestMapping(value = "getMachineListByTeam", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getMachineListByTeam(@RequestParam("teamName") String teamName,
    		@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit
    		) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
	}
	
	@RequestMapping(value = "getMachineListByType", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getMachineListByType(@RequestParam("typeName") String typeName,
    		@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit
    		) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
	}
	
	@RequestMapping(value = "addMachine", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> addMachine(@RequestParam("machineInfoDTO") MachineInfoDTO machineInfoDTO) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
	}
	
}
