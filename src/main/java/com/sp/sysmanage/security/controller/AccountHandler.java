package com.sp.sysmanage.security.controller;

import com.sp.sysmanage.common.service.Interface.SystemLogService;
import com.sp.sysmanage.common.util.Response;
import com.sp.sysmanage.common.util.ResponseFactory;
import com.sp.sysmanage.domain.UserInfoDTO;
import com.sp.sysmanage.exception.SystemLogServiceException;
import com.sp.sysmanage.exception.UserAccountServiceException;
import com.sp.sysmanage.security.service.Interface.AccountService;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountHandler {

	private static Logger log = Logger.getLogger(AccountHandler.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SystemLogService systemLogService;
	
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "password";
    
    @RequestMapping(value="login", method=RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> login(@RequestBody Map<String, Object> user) {
    	
    	// Initialize Response
    	Response response = ResponseFactory.newInstance();
    	String result = Response.RESPONSE_RESULT_ERROR;
    	String errorMsg = "";
    	
    	// Get current user's subject
    	Subject currentUser = SecurityUtils.getSubject();

    	if (currentUser != null && !currentUser.isAuthenticated()) {
    		
            String name = (String) user.get(USER_NAME);
            String password = (String) user.get(USER_PASSWORD);
            
            Session session = currentUser.getSession();
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);   		
    		
            try {
                currentUser.login(token);

                // set userInfo into session
                UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
                // set login IP
                userInfo.setAccessIP(session.getHost());

                // Add a log into system log repository
                systemLogService.insertAccessRecord(userInfo.getUserName(),
                		userInfo.getAccessIP(),
                		SystemLogService.ACCESS_LOGIN);

                result = Response.RESPONSE_RESULT_SUCCESS;

            } catch (UnknownAccountException e) {
                errorMsg = "unknownAccount";
                log.error("unknownAccount");
            } catch (IncorrectCredentialsException e) {
                errorMsg = "incorrectCredentials";
                log.error("incorrectCredentials");
            } catch (AuthenticationException e) {
                errorMsg = "authenticationError";
                log.error("authenticationError");
                e.printStackTrace();
            } catch (SystemLogServiceException e) {
            	errorMsg = "internalError";
            	log.error("Can't log the into system log");
            } finally {
                // clean the user info in session
                if (result.equals(Response.RESPONSE_RESULT_ERROR)){
                    session.setAttribute("userInfo", null);
                }
            }    		
    		
    	} else {
    		errorMsg = "Already login";
    	}
    	
    	log.error("Login result: " + result.toString());
    	
    	response.setResponseResult(result);
    	response.setResponseMsg(errorMsg);
    	
    	return response.generateResponse();
    	
    }
    
    
    @RequestMapping(value="logout", method=RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> logout() {

        Response response = ResponseFactory.newInstance();

        Subject currentSubject = SecurityUtils.getSubject();
        if (currentSubject != null && currentSubject.isAuthenticated()) {
            currentSubject.logout();
            response.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        } else {
            response.setResponseResult(Response.RESPONSE_RESULT_ERROR);
            response.setResponseMsg("did not login");
        }

        return response.generateResponse();
    }
	
}
