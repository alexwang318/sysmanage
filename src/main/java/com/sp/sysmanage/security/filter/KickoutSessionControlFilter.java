package com.sp.sysmanage.security.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

public class KickoutSessionControlFilter extends AccessControlFilter {
	
	private String kickOutUrl;
	private boolean kickOutAfter;
	private int maxSessionNum;
	
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;
    
    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public void setKickOutAfter(boolean kickOutAfter) {
        this.kickOutAfter = kickOutAfter;
    }

    public void setMaxSessionNum(int maxSessionNum) {
        this.maxSessionNum = maxSessionNum;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("sessionCache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated() && !subject.isRemembered())
            return true;

        Session session = subject.getSession();
        String userName = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        Deque<Serializable> deque = cache.get(userName);
        if (deque == null) {
            deque = new LinkedList<>();
            cache.put(userName, deque);
        }

        if (!deque.contains(sessionId) && session.getAttribute("kickOut") == null) {
            deque.push(sessionId);
        }

        while (deque.size() > maxSessionNum) {
            Serializable kickOutSessionId;
            if (kickOutAfter) {
                kickOutSessionId = deque.removeFirst();
            } else {
                kickOutSessionId = deque.removeLast();
            }

            try {
                Session kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
                if (kickOutSession != null) {
                    kickOutSession.setAttribute("kickOut", true);
                }
            } catch (Exception e) {
                // do logging
                e.printStackTrace();
            }
        }

        if (session.getAttribute("kickOut") != null && Boolean.TRUE.equals(session.getAttribute("kickOut"))) {
            try {
                subject.logout();

                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-with"))) {
                    WebUtils.issueRedirect(httpServletRequest, httpServletResponse, kickOutUrl);
                } else {
                    httpServletResponse.setStatus(430);
                }

            } catch (Exception e) {
                // do nothing
            }
            return false;
        }

        return true;
    }

}
