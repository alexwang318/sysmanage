package com.oracle.sp.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.sp.common.util.Response;
import com.oracle.sp.common.util.ResponseFactory;
import com.oracle.sp.domain.LabLocationDO;
import com.oracle.sp.domain.MachineInfoDTO;
import com.oracle.sp.domain.ServerTypeDO;
import com.oracle.sp.domain.ServerTypeDTO;
import com.oracle.sp.exception.LabLocationServiceException;
import com.oracle.sp.exception.MachineManageServiceException;
import com.oracle.sp.exception.ServerTypeServiceException;
import com.oracle.sp.exception.UserManageServiceException;
import com.oracle.sp.sysmanage.service.Interface.LabLocationService;
import com.oracle.sp.sysmanage.service.Interface.ServerTypeService;

@RequestMapping(value="/machineManage")
@Controller
public class machineManageHandler {
  
    @Autowired
    private LabLocationService labLocationService;
    
    @Autowired
    private ServerTypeService serverTypeService;
    
    private final String TYPE_PIC_PATH = "image" + File.separator + "server_type_pic" + File.separator;
    
    
    private static Logger log = Logger.getLogger(machineManageHandler.class);
    
    
    
    
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
	   		log.error("Add Lab location exception");
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getLabList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLabList(@RequestParam("team") String team, 
    		@RequestParam("offset") int offset, 
    		@RequestParam("limit") int limit) 
    				throws MachineManageServiceException {
		
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	List<LabLocationDO> rows = null;
	   	long total = 0;
	   	
	   	log.error("Get Lab for: " + team);
	   	
	   	try {
	   		Map<String, Object> queryResult = labLocationService.getAll(offset, limit);
		   	if (queryResult != null) {
		   		rows = (List<LabLocationDO>)(queryResult.get("data"));
		   		total = (long)queryResult.get("total");
		   	}
		   	
		   	response.setUserInfo("rows", rows);
		   	response.setResponseTotal(total);
	   		result = Response.RESPONSE_RESULT_SUCCESS; 		
	   	} catch (LabLocationServiceException e) {
	   		// FIXME: add some error handler here.
	   		log.error("Get exception from service layer");
	   	}  	
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
	
	
	/*
	 * To see whether this name is used before.
	 */
	@RequestMapping(value = "verifyLabName", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean verifyLabName(@RequestParam("name") String name) throws UserManageServiceException {
        boolean result = true;

        log.error("verify User name: " + name + " from DB, to see whether it's used before");
        
        try {
        	LabLocationDO labLocationDO = labLocationService.getLocationByName(name);
        	if (labLocationDO != null) {
        		result = false;
        	}
        } catch (LabLocationServiceException e) {
        	// FIXME: Add some debug info here?
        	log.error("verify Lab name exception");
        }
        
        
        return result;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getServerTypeList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getServerTypeList(@RequestParam("offset") int offset, 
    		@RequestParam("limit") int limit) 
    				throws MachineManageServiceException {
		
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	List<ServerTypeDO> serverTypeDOS = null;
	   	List<ServerTypeDTO> serverTypeDTOS = null;
	   	long total = 0;
	   	
	   	String picPath = System.getProperty("webapp.root") + TYPE_PIC_PATH;
	   	
	   	log.error("Get type list now");
	   	log.error("Picture Path: " + picPath);
	   	
	   	try {
	   		Map<String, Object> queryResult = serverTypeService.selectAll(offset, limit);
	   		
		   	if (queryResult != null) {
		   		serverTypeDOS = (List<ServerTypeDO>)(queryResult.get("data"));
		   		total = (long)queryResult.get("total");
		   	}
		   	
		   	serverTypeDTOS = new ArrayList<>(serverTypeDOS.size());
		   	
		   	for(ServerTypeDO typeDO : serverTypeDOS) {
		   		ServerTypeDTO typeDTO = new ServerTypeDTO();
		   		typeDTO.setName(typeDO.getName());
		   		typeDTO.setUrl(typeDO.getUrl());
		   		typeDTO.setDescription(typeDO.getDescription());
		   		
		   		// Save picture into a file under /image/server_type_pic and then transfer path to front-web.
		   		String fileName = picPath + typeDO.getName();
		   		
		   		log.error("Path: " + fileName);
		   		
		   		File fp = new File(fileName);
		   		if (!fp.exists()) {
		   			try {
		   				FileOutputStream out = new FileOutputStream(fp);
		   				log.error("Be about to write data, length: " + typeDO.getPicture().length);
		   				out.write(typeDO.getPicture());		   				
		   				out.close();
		   			} catch (IOException e) {
		   				log.error("IO exception here");
		   			}
		   		} else {
		   			log.error("file is exist there");
		   		}
		   		typeDTO.setPicturePath(TYPE_PIC_PATH + typeDO.getName());
		   		
		   		serverTypeDTOS.add(typeDTO);
		   	}
	   		result = Response.RESPONSE_RESULT_SUCCESS; 		
	   	} catch (ServerTypeServiceException e) {
	   		// FIXME: add some error handler here.
	   		log.error("Get exception from service layer");
	   		serverTypeDTOS = new ArrayList<>();
	   	}  	
	   	
	   	response.setUserInfo("rows", serverTypeDTOS);
	   	response.setResponseTotal(total);
	   	response.setResponseResult(result);
    	return response.generateResponse();
	}
	
	/*
	 * To see whether this name is used before.
	 */
	@RequestMapping(value = "verifyServerTypeName", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean verifyServerTypeName(@RequestParam("name") String name) throws UserManageServiceException {
        boolean result = true;

        log.error("verify User name: " + name + " from DB, to see whether it's used before");
        
        try {
        	ServerTypeDO serverTypeDO = serverTypeService.getByName(name);
        	if (serverTypeDO != null) {
        		result = false;
        	}
        } catch (ServerTypeServiceException e) {
        	// FIXME: Add some debug info here?
        	log.error("verify type name exception");
        }
        
        
        return result;
	}

	@RequestMapping(value = "addServerType", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> addServerType(@RequestParam("name") String name,
    		@RequestParam("url") String url,
    		@RequestParam("description") String description,
    		@RequestParam("picture") MultipartFile picture) throws MachineManageServiceException {
	   	
		Response response = ResponseFactory.newInstance();

		if ((name != null) &&
			(!name.isEmpty()) &&
			(!picture.isEmpty())) {
			
			try {
				byte[] bytes = picture.getBytes();
				
				log.error("Get img data length: " + bytes.length);

			   	// To see whether there is a lab name in DB.
			   	if (null == serverTypeService.getByName(name)) {
			   		ServerTypeDO serverTypeDO = new ServerTypeDO();
				   	
				   	//ID is auto increment, so we set a invalid here.
			   		serverTypeDO.setId(0);
			   		serverTypeDO.setName(name);
			   		serverTypeDO.setUrl(url);
			   		serverTypeDO.setDescription(description);
			   		serverTypeDO.setPicture(bytes);
				   	
			   		log.error("Try to insert machine type: " + name + " into DB now.");
			   		serverTypeService.insert(serverTypeDO);
				   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
			   	} else {
			   		response.setResponseResult(Response.RESPONSE_RESULT_ERROR);
			   	}
		   	} catch (ServerTypeServiceException |IOException e) {
		   		//FIXME: add some exception handler here.
		   		log.error("Add Lab location exception");
		   	}
		}
    	
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
