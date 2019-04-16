package com.erp.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SessionTracker {
	public static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	private AtomicLong sessionCount = new AtomicLong(0);

	void incrementSessionCount() {
		LOGGER.warn("Increment the session count");
		sessionCount.incrementAndGet();
	}

	public static HttpSession getHttpSession(String sessionID) {
		return map.get(sessionID);
	}

	public static HttpSession removeHttpSession(String sessionID) {
		return map.remove(sessionID);
	}

	public long getSessionCount() {
		LOGGER.warn("Current session count " + sessionCount.get());
		return sessionCount.get();
	}

	void decrementSessionCount() {
		sessionCount.decrementAndGet();
	}

	private static final Logger LOGGER = Logger.getLogger(SessionTracker.class);

	public static Map<String, HttpSession> getMap() {
		return map;
	}

	public static void setMap(Map<String, HttpSession> map) {
		SessionTracker.map = map;
	}
}
