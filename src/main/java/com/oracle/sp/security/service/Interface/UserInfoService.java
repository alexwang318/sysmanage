package com.oracle.sp.security.service.Interface;

import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserInfoServiceException;

import java.util.List;
import java.util.Set;

public interface UserInfoService {
	
	/**
	 * Get user info by user Name
	 * @param userName
	 * @return User info
	 * @throws UserInfoServiceException
	 */
	UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException;
	
	/**
	 * Get all user info
	 * @return A list of all users' info.
	 * @throws UserInfoServiceException
	 */
	List<UserInfoDTO> getAllUserInfo() throws UserInfoServiceException;
	
	/**
	 * Update the user info	
	 * @param userInfoDTO
	 * @throws UserInfoServiceException
	 */
	void updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;
	
	/**
	 * Delete user info
	 * @param userID
	 * @throws UserInfoServiceException
	 */
	void deleteUserInfo(String userName) throws UserInfoServiceException;
	
	/**
	 * Insert a user info.
	 * @param userInfoDTO
	 * @return success or fail
	 * @throws UserInfoServiceException
	 */
	boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;
	
	/**
	 * Get the roles of a user.
	 * @param userID
	 * @return
	 * @throws UserInfoServiceException
	 */
	Set<String> getUserRoles(String userName) throws UserInfoServiceException;
	
	

}
