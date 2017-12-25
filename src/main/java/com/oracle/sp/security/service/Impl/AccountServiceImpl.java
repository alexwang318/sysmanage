package com.oracle.sp.security.service.Impl;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.UserAccountServiceException;
import com.oracle.sp.exception.UserInfoServiceException;
import com.oracle.sp.security.service.Interface.AccountService;
import com.oracle.sp.security.service.Interface.UserInfoService;
import com.oracle.sp.security.utils.MD5Util;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private UserInfoService userInfoService;
	
    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String REPEAT_PASSWORD = "rePassword";

	@Override
	public void passwordModify(String userName, Map<String, Object> passwordInfo) throws UserAccountServiceException {
        if (passwordInfo == null)
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

        String rePassword = (String) passwordInfo.get(REPEAT_PASSWORD);
        String newPassword = (String) passwordInfo.get(NEW_PASSWORD);
        String oldPassword = (String) passwordInfo.get(OLD_PASSWORD);
        if (rePassword == null || newPassword == null || oldPassword == null)
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

        try {
            UserInfoDTO user = userInfoService.getUserInfo(userName);
            if (user == null) {
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);
            }

            if (!newPassword.equals(rePassword)) {
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_UNMATCH);
            }

            // FIXME: Please be note the the password store in DB is MD5 encrypted?
            String password;
            password = MD5Util.MD5(oldPassword + userName);
            if (!password.equals(user.getPassword()))
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

            password = MD5Util.MD5(newPassword + userName);

            user.setPassword(password);
            user.setFirstLogin(false);
            userInfoService.updateUserInfo(user);

            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            session.setAttribute("firstLogin", false);

        } catch (NullPointerException | UserInfoServiceException e) {
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);
        }

	}

}
