package com.oracle.sp.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.sp.common.util.Response;
import com.oracle.sp.common.util.ResponseFactory;
import com.oracle.sp.dao.security.GroupMapper;
import com.oracle.sp.dao.security.RolesMapper;
import com.oracle.sp.domain.GroupDO;
import com.oracle.sp.domain.RoleDO;
import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;
import com.oracle.sp.exception.UserManageServiceException;
import com.oracle.sp.security.service.Interface.UserInfoService;

@RequestMapping(value="/userManage")
@Controller
public class userManageHandler {

	@Autowired
	private UserInfoService userInfoService;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private GroupMapper groupMapper;
	
    private static final String SEARCH_SUPER_USER = "searchSuperUser";
    private static final String SEARCH_USER = "searchUser";
    private static final String SEARCH_ALL = "searchAll";
	
    private static Logger log = Logger.getLogger(userManageHandler.class);
    
    
	private Map<String, Object> query(String searchRole,
			int offset, 
			int limit) {
		
        Map<String, Object> queryResult = null;

        try {
	        switch (searchRole) {
	            case SEARCH_SUPER_USER:
	                queryResult = userInfoService.getUsersByRole("superUser", offset, limit);
	                break;
	            case SEARCH_USER:
	                queryResult = userInfoService.getUsersByRole("user", offset, limit);
	                break;
	            case SEARCH_ALL:
	                queryResult = userInfoService.getAllUserInfo(offset, limit);
	                break;
	            default:
	                // do other thing
	            	log.error("do nothing!!!");
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
	   	
	   	log.error("Get request from front-end, role: " + role + ", offset: " + offset + ", limit: " + limit);
	   	
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
    Map<String, Object> addUser(@RequestBody Map<String, Object> userInfo) throws UserManageServiceException {
	   
	   	// Initialize Response
	   	Response response = ResponseFactory.newInstance();
	   	String result = Response.RESPONSE_RESULT_ERROR; 
	   	
	   	UserInfoDTO userInfoDTO = new UserInfoDTO();
	   	String userName = (String) userInfo.get("userName");
	   	String password = (String) userInfo.get("password");
	   	String email = (String) userInfo.get("email");
	   	List<String> role = new ArrayList<>();
	   	role.add((String) userInfo.get("role"));
	   	List<String> group = new ArrayList<>();
	   	group.add((String) userInfo.get("group"));
	   	Date date = new Date();
	   	
	   	userInfoDTO.setUserID(0);
	   	userInfoDTO.setAccessIP("");
	   	userInfoDTO.setEmail(email);
	   	userInfoDTO.setFirstLogin(true);
	   	userInfoDTO.setGroup(group);
	   	userInfoDTO.setRole(role);
	   	userInfoDTO.setPassword(password);
	   	userInfoDTO.setUserName(userName);
	   	userInfoDTO.setLastLoginDate(date.toString()); 
	   	userInfoDTO.setStatus(0);
	   	
	   	try {
		   	log.error("Will add user: " + userInfoDTO.getUserName() + " into the DB");
		   	log.error("User info: " + userInfoDTO.toString());
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
        } catch (Exception e) {
        	// FIXME: Add some debug info here?
        }

        response.setResponseResult(result);
        return response.generateResponse();
    }
    
    @RequestMapping(value = "updateUserStatus", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> updateUserStatus(@RequestParam("userName") String userName,
    		@RequestParam("status") String statusString) throws UserInfoServiceException {
    	
        Response response = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_SUCCESS;
        Integer status = 0;
        UserInfoDTO userInfoDTO = null;

        log.error("Get user: " + userName + "to update status: " + statusString);
        
        try {
        	userInfoDTO = userInfoService.getUserInfo(userName);
        	if ((userInfoDTO != null) && StringUtils.isNumeric(statusString)) {
            	status = Integer.parseInt(statusString);

        		userInfoDTO.setStatus(status);
        		log.error("Call service to update user info now");
        		userInfoService.updateUserInfo(userInfoDTO);
        	} else {
        		result = Response.RESPONSE_RESULT_ERROR;
        	}
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }

        response.setResponseResult(result);
        return response.generateResponse();
    }
    
	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> deleteUser(@RequestParam("userName") String userName) throws UserManageServiceException {
        Response response = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        
        log.error("Delete User: " + userName + "from DB");
        
        try {
        	result = userInfoService.deleteUserInfo(userName) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }
        
        response.setResponseResult(result);
        return response.generateResponse();
	}
	
	/*
	 * To see whether this user name is used before.
	 */
	@RequestMapping(value = "verifyUserName", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean verifyUserName(@RequestParam("userName") String userName) throws UserManageServiceException {
        boolean result = true;
        UserInfoDTO userInfoDTO = null;

        log.error("verify User name: " + userName + " from DB, to see whether it's used before");
        
        try {
        	userInfoDTO = userInfoService.getUserInfo(userName);
        	if (userInfoDTO != null) {
        		result = false;
        	}
        } catch (UserInfoServiceException e) {
        	// FIXME: Add some debug info here?
        }
        
        
        return result;
	}
	
    @RequestMapping(value = "getRoleList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getRoleList() throws UserManageServiceException {
	   
	   	// Initialize Response
	   	Response response = ResponseFactory.newInstance();
	   	
	   	List<RoleDO> roleDOS = null;
     	List<String> roles = new ArrayList<>();
	   	
	   	log.error("Get role list");
	   	
	   	roleDOS = rolesMapper.getAllRoles();
	   	for(RoleDO roleDO : roleDOS) {
	   		roles.add(roleDO.getRoleName());
	   	}

	   	response.setResponseData(roles);
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
    }
    
    @RequestMapping(value = "getGroupList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getGroupList() throws UserManageServiceException {
	   
	   	// Initialize Response
	   	Response response = ResponseFactory.newInstance();
	   	
	   	List<GroupDO> groupDOS = null;
     	List<String> groups = new ArrayList<>();
	   	
	   	log.error("Get group list");
	   	
	   	groupDOS = groupMapper.getAllGroups();
	   	for(GroupDO groupDO : groupDOS) {
	   		groups.add(groupDO.getGroupName());
	   	}

	   	response.setResponseData(groups);
	   	response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
    	
    	return response.generateResponse();
    }
	
	// FIXME: For user info import and export function, will add later.
}
