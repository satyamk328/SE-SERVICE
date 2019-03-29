package com.erp.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	public static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		 
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		
		final PrintWriter writer = response.getWriter();

		final String result = mapper.writeValueAsString("HTTP Status 401 - " + authEx.getMessage());
		writer.write(result);
		writer.flush();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("satyam");
		super.afterPropertiesSet();
	}

}
