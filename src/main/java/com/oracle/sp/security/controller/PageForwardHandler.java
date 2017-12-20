package com.oracle.sp.security.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Page redirection handler
 * 
 * @author binwan
 *
 */
@RequestMapping("/")
@Controller
public class PageForwardHandler {
	
	private static Logger log = Logger.getLogger(PageForwardHandler.class);

	@RequestMapping("login")
	public String loginPageForward() {
		Subject currentSubject = SecurityUtils.getSubject();
		
		log.error("Login Page Forward now");
		
		if (!currentSubject.isAuthenticated()) {
			return "login";
		} else {
			return "mainPage";
		}
	}
	
	@RequestMapping("")
	public String showLoginView() {
		log.error("forward to mainPage directly!");
		return "mainPage";
	}
}
