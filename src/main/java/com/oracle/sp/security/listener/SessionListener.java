package com.oracle.sp.security.listener;

import com.oracle.sp.common.service.Interface.SystemLogService;
import com.oracle.sp.domain.UserInfoDTO;
import com.oracle.sp.exception.SystemLogServiceException;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Shiro Session listener
 * Called when Shiro's Session is created, logout or timeout.
 * 
 * @author binwan
 *
 */
public class SessionListener extends SessionListenerAdapter {
	
	@Autowired
	private SystemLogService systemLogService;
	
    @Override
    public void onStart(Session session) {
        super.onStart(session);
    }

    @Override
    public void onStop(Session session) {
        super.onStop(session);
        sessionDestroyedLog(session);
    }

    @Override
    public void onExpiration(Session session) {
        super.onExpiration(session);
        sessionDestroyedLog(session);
    }
    
    private void sessionDestroyedLog(Session session) {

        UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
        if (userInfo != null) {
            try {

                systemLogService.insertAccessRecord(userInfo.getUserName(),
                        userInfo.getAccessIP(), SystemLogService.ACCESS_LOGOUT);
            } catch (SystemLogServiceException e) {
                // do log
            }
        }
    }
}
