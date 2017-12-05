package com.sp.sysmanage.security.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sp.sysmanage.domain.UserInfoDTO;
import com.sp.sysmanage.exception.UserInfoServiceException;
import com.sp.sysmanage.security.service.Interface.UserInfoService;
import com.sp.sysmanage.security.utils.MD5Util;

public class UserAuthorizingRealm extends AuthorizingRealm {
	
	@Autowired
	private UserInfoService userInfoService;
	
	private Logger logger = Logger.getLogger(UserAuthorizingRealm.class);
	
	
	/**
	 * Get the authorization.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection printcipalCollection) {
		Set<String> roles = new HashSet<>();
		
		Subject currentSubject = SecurityUtils.getSubject();
		Session session = currentSubject.getSession();
		UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
		roles.addAll(userInfo.getRole());
		
		return new SimpleAuthorizationInfo(roles);
	}

	/**
	 * Do login verification.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
		AuthenticationException {
		
		String realmName = getName();
		String credentials = "";
		
		logger.error("Start to do login authenticating now");
		
		try {
			UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
			String userName = token.getUsername();
			
			logger.error("useName:" + userName);
			
			// Call DAO service to get user Info DTO.
			// Please see UserInfoServiceImpl
            UserInfoDTO userInfoDTO = userInfoService.getUserInfo(userName);
            
            
            if (userInfoDTO != null) {
                Subject currentSubject = SecurityUtils.getSubject();
                Session session = currentSubject.getSession();
                
                logger.error("name:" + userInfoDTO.getUserName());

                session.setAttribute("userInfo", userInfoDTO);
                
                //Get the real password from userInfoDTO which is from
                //Database.
                String password = userInfoDTO.getPassword();

                logger.error("passowrd: " + userInfoDTO.getPassword());
                
                if (password != null) {
                    credentials = MD5Util.MD5(password);
                    credentials = MD5Util.MD5(credentials + userInfoDTO.getUserName());
                }

                userInfoDTO.setPassword(null);
                
            } else {
            	logger.error("Can't get the user Info DTO");
            	throw new UnknownAccountException();
            	
            }

            return new SimpleAuthenticationInfo(userName, credentials, realmName);

        } catch (UserInfoServiceException e) {
            throw new AuthenticationException();
        }
	}

}
