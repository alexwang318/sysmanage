package com.oracle.sp.security.filter;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import com.oracle.sp.security.realms.UserAuthorizingRealm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AnyOfRolesAuthorizationFilter extends RolesAuthorizationFilter {   
	
	private Logger log = Logger.getLogger(AnyOfRolesAuthorizationFilter.class);
	
	@Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
            Object mappedValue) throws IOException{

		final Subject subject = getSubject(request, response);
		final String[] rolesArray = (String[]) mappedValue;
		
				
		if (rolesArray == null || rolesArray.length == 0){
			// no roles specified, so nothing to check - allow access
			//System.out.println("no roles");
			log.error("no roles specified, so nothing to check - allow access");
			return true;
		}
		
		for (String role : rolesArray){
			//System.out.println(role);
			if (subject.hasRole(role)){
				log.error("has role: " + role);
				return true;
			}
		}
		
		log.error("Can't get the authority!!");
		
		return false;
	}
	
}
