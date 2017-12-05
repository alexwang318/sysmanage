package com.sp.sysmanage.security.service.Interface;

import java.util.Map;

import com.sp.sysmanage.exception.UserAccountServiceException;

public interface AccountService {

	/**
	 * Modify Password
	 * @param userID
	 * @param passwordInfo
	 * @throws UserAccountServiceException
	 */
	public void passwordModify(String userName, Map<String, Object> passwordInfo)
			throws UserAccountServiceException;
}
