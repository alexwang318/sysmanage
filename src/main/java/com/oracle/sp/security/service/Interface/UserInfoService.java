package com.oracle.sp.security.service.Interface;

import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;

import java.util.Map;
import java.util.Set;

public interface UserInfoService {
	
	UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException;
	
	Map<String, Object> getAllUserInfo() throws UserInfoServiceException;
	
	Map<String, Object> getAllUserInfo(int offset, int limit) throws UserInfoServiceException;
	
	boolean updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;
	
	boolean deleteUserInfo(String userName) throws UserInfoServiceException;
	
	boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;
	
	Set<String> getUserRoles(String userName) throws UserInfoServiceException;

	Map<String, Object> getUsersByRole(String role, int offset, int limit) throws UserInfoServiceException;

}
