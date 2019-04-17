package com.erp.auth;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class SessionCounterListener implements HttpSessionListener {

	private static int totalActiveSessions;

	public static int getTotalActiveSession() {
		return totalActiveSessions;
	}

	static Logger logger = LoggerFactory.getLogger(SessionCounterListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getSession().getServletContext());

		arg0.getSession().setMaxInactiveInterval(30 * 60);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getSession().getServletContext());

	}
}
