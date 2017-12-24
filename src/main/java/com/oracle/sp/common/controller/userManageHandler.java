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
import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;
import com.oracle.sp.exception.UserManageServiceException;
import com.oracle.sp.security.service.Interface.UserInfoService;

@RequestMapping(value="/userManage")
@Controller
public class userManageHandler {

	@Autowired
	private UserInfoService userInfoService;
	
    private static final String SEARCH_ADMIN = "searchAdmin";
    private static final String SEARCH_USER = "searchUser";
    private static final String SEARCH_ALL = "searchAll";
	
	private Map<String, Object> query(String searchRole,
			int offset, 
			int limit) {
		
        Map<String, Object> queryResult = null;

        try {
	        switch (searchRole) {
	            case SEARCH_ADMIN:
	            case SEARCH_USER:
	                queryResult = userInfoService.getUsersByRole(searchRole, offset, limit);
	                break;
	            case SEARCH_ALL:
	                queryResult = userInfoService.getAllUserInfo(offset, limit);
	                break;
	            default:
	                // do other thing
	                break;
	        }
        } catch (UserInfoServiceException e) {
            // FIXME:Add some log here?
        }
        
        return queryResult;
	}
   
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getUserList(@RequestParam("role") String role,
    		@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit
    		) throws UserManageServiceException {
	   
	   	// Initialize Response
	   	Response response = ResponseFactory.newInstance();
  	
	   	List<UserInfoDTO> rows = null;
	   	long total = 0;
	   	
	   	
	   	Map<String, Object> queryResult = query(role, offset, limit);
	   	
	   	if (queryResult != null) {
	   		rows = (List<UserInfoDTO>)queryResult.get("data");
	   		total = (long)queryResult.get("total");
	   	}
	   	
	   	response.setUserInfo("rows", rows);
	   	response.setResponseTotal(total);
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
   }
    

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> addUser(@RequestBody UserInfoDTO userInfoDTO) throws UserManageServiceException {
	   
	   	// Initialize Response
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR;
	   	
	   	try {
	   		result = userInfoService.insertUserInfo(userInfoDTO) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
	   	} catch (UserInfoServiceException e) {
	   		// FIXME: Add some debug info here?
	   	}
	   	
	   	response.setResponseResult(result);
    	return response.generateResponse();
   }
    
	@RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getUserInfo(@RequestParam("userName") String userName) throws UserManageServiceException {
        Response response = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        UserInfoDTO userInfoDTO = null;
        
        try {
        	userInfoDTO = userInfoService.getUserInfo(userName);
        	if (userInfoDTO != null) {
        		result = Response.RESPONSE_RESULT_SUCCESS;
        	}
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }
        
        response.setResponseResult(result);
        response.setResponseData(userInfoDTO);
        return response.generateResponse();
	}
	
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        Response response = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        try {
        	result = userInfoService.updateUserInfo(userInfoDTO) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }

        response.setResponseResult(result);
        return response.generateResponse();
    }
    
	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> deleteUser(@RequestParam("deleteUser") String userName) throws UserManageServiceException {
        Response response = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        
        try {
        	result = userInfoService.deleteUserInfo(userName) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }
        
        response.setResponseResult(result);
        return response.generateResponse();
	}
	
	// FIXME: For user info import and export function, will add later.
}
