package com.erp.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.erp.audit.dao.UserTokenDao;
import com.erp.service.TokenAuthenticationService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
	SessionTracker sessionTracker;

    @Autowired
    private UserTokenDao jwtDao;
    
	@Autowired
	TokenAuthenticationService authenticationService;

	public static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		try {
			sessionTracker.removeHttpSession(authenticationService.getSessionId(httpServletRequest));
			 String token = httpServletRequest.getHeader(TokenAuthenticationService.AUTHORIZATION_HEADER);
	            if (token != null && token.contains(TokenAuthenticationService.BEARER_TOKEN_PREFIX)) {
	                token = authenticationService.getAuthenticationToken(token);
	            	ChannelDetails channelDetails = channelService.getChannelHeaderInfo();

	                final JwtModel jwtModel = new JwtModel();
	                jwtModel.setToken(token);
	                jwtModel.setValid(false);
	                jwtModel.setChannelName(channelDetails.getChannelName());
	                jwtModel.setChannelType(channelDetails.getChannelType());
	                jwtDao.updateJwtIsValid(jwtModel);
	                
	                logEvent(httpServletRequest,true);
	            }
	            
		} catch (Exception e1) {
		    log.error("Exception {}", e1);
		}
		if (authentication != null && authentication.getDetails() != null) {
			try {
				sessionTracker.getHttpSession(authenticationService.getSessionId(httpServletRequest)).invalidate();
				sessionTracker.removeHttpSession(authenticationService.getSessionId(httpServletRequest));
				log.info("User Successfully Logout");
			} catch (Exception e) {
			    log.error("Exception {}", e);
				httpServletRequest.getSession().invalidate();
			}
		}

		PrintWriter writer = httpServletResponse.getWriter();
		RestResponse<String> responseObj = new RestResponse<>(null,
				new RestStatus<>("200", "User Successfully Logout"));
		String result = mapper.writeValueAsString(responseObj);
		log.info(result);
		writer.write(mapper.writeValueAsString(responseObj));
		writer.flush();

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}

}
