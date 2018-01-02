package com.oracle.sp.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.sp.common.util.Response;
import com.oracle.sp.common.util.ResponseFactory;
import com.oracle.sp.domain.LabLocationDO;
import com.oracle.sp.domain.MachineInfoDTO;
import com.oracle.sp.exception.LabLocationServiceException;
import com.oracle.sp.exception.MachineManageServiceException;
import com.oracle.sp.sysmanage.service.Interface.LabLocationService;

@RequestMapping(value="/machineManage")
@Controller
public class machineManageHandler {
  
    @Autowired
    private LabLocationService labLocationService;
    
	@RequestMapping(value = "addLabLocation", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> addLabLocation(@RequestParam("name") String name,
    		@RequestParam("description") String description) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	
	   	try {
		   	// To see whether there is a lab name in DB.
		   	if (null == labLocationService.getLocationByName(name)) {
			   	LabLocationDO labLocationDO = new LabLocationDO();
			   	
			   	//ID is auto increment, so we set a invalid here.
			   	labLocationDO.setId(0);
			   	labLocationDO.setName(name);
			   	labLocationDO.setDescription(description);
			   	
			   	labLocationService.insert(labLocationDO);
			   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
		   	} else {
		   		response.setResponseResult(Response.RESPONSE_RESULT_ERROR);
		   	}
	   	} catch (LabLocationServiceException e) {
	   		//FIXME: add some exception handler here.
	   	}
    	
    	return response.generateResponse();
	}	
	
	/*
	 * If you specify a name, it return a LabLocationDO,
	 * But if you don't specify a name(name is empty), it will return error.
	 */	
	@RequestMapping(value = "getLabLocation", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLabLocation(@RequestParam("name") String name) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	
	   	try {
		   	// To see whether there is a lab name in DB.
		   	if (name != null && !name.isEmpty()) {
	   			LabLocationDO labLocationDO = labLocationService.getLocationByName(name);
	   			response.setResponseData(labLocationDO);

		   		result = Response.RESPONSE_RESULT_SUCCESS; 		
		   	} else {
		   		result = Response.RESPONSE_RESULT_ERROR;
		   	}
	   	} catch (LabLocationServiceException e) {
	   		// FIXME: add some error handler here.
	   	}  	
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLabLocation(@RequestParam("offset") int offset, @RequestParam("limit") int limit) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	
	   	try {
   			List<LabLocationDO> labLocationDOS = labLocationService.getAll(offset, limit);
   			response.setResponseData(labLocationDOS);
	   		result = Response.RESPONSE_RESULT_SUCCESS; 		
	   	} catch (LabLocationServiceException e) {
	   		// FIXME: add some error handler here.
	   	}  	
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
	
	/*
	 * If you specify a name, it return a LabLocationDO,
	 * But if you don't specify a name(name is empty), we suppose you want to 
	 * get all, so return List<LabLocationDO>.
	 */	
	@RequestMapping(value = "updateLabLocation", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateLabLocation(@RequestBody LabLocationDO labLocationDO) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	
	   	try {
		   	// To see whether there is a lab name in DB.
		   	if (labLocationDO != null) {
		   		boolean rc = labLocationService.update(labLocationDO);
	   			if (rc)
	   				result = Response.RESPONSE_RESULT_SUCCESS; 		
		   	} else {
		   		result = Response.RESPONSE_RESULT_ERROR;
		   	}
	   	} catch (LabLocationServiceException e) {
	   		// FIXME: add some error handler here.
	   	}
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
	
	@RequestMapping(value = "deleteLabLocation", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> deleteLabLocation(@RequestParam("name") String name) throws MachineManageServiceException {
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	
	   	try {
		   	// To see whether there is a lab name in DB.
		   	if ((name != null) && !name.isEmpty()) {
		   		boolean rc = labLocationService.deleteByName(name);
	   			if (rc)
	   				result = Response.RESPONSE_RESULT_SUCCESS; 		
		   	} else {
		   		result = Response.RESPONSE_RESULT_ERROR;
		   	}
	   	} catch (LabLocationServiceException e) {
	   		// FIXME: add some error handler here.
	   	}
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
    
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
