package com.sp.sysmanage.security.service.Interface;

import com.sp.sysmanage.domain.UserInfoDTO;
import com.sp.sysmanage.exception.UserInfoServiceException;

import java.util.List;
import java.util.Set;

public interface UserInfoService {
	
	/**
	 * Get the user info by user ID
	 * 
	 * @param userID
	 * @return User Info
	 * @throws UserInfoServiceException
	 */
	UserInfoDTO getUserInfo(Integer userID) throws UserInfoServiceException;
	
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
	void deleteUserInfo(Integer userID) throws UserInfoServiceException;
	
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
	Set<String> getUserRoles(Integer userID) throws UserInfoServiceException;
	
	

}
