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

import com.erp.audit.dao.UserJwtTokenDao;
import com.erp.audit.model.JwtModel;
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
    private UserJwtTokenDao jwtDao;
    
	@Autowired
	TokenAuthenticationService tokenService;

	public static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		try {
            String token = httpServletRequest.getHeader(TokenAuthenticationService.AUTHORIZATION_HEADER);
            if (token != null && token.contains(TokenAuthenticationService.BEARER_TOKEN_PREFIX)) {
                token = tokenService.getAuthenticationToken(token);
                //sessionTracker.removeHttpSession(tokenService.getSessionId(httpServletRequest));
                
                final JwtModel jwtModel = new JwtModel();
                jwtModel.setToken(token);
                jwtModel.setValid(false);
                jwtDao.updateJwtIsValid(jwtModel);
            }

            if (authentication != null && authentication.getDetails() != null) {
            	try {
            		httpServletRequest.getSession().invalidate();
    				//sessionTracker.getHttpSession(tokenService.getSessionId(httpServletRequest)).invalidate();
    				//sessionTracker.removeHttpSession(tokenService.getSessionId(httpServletRequest));
    				log.info("User Successfully Logout");
    			} catch (Exception e) {
    			    log.error("Exception {}", e);
    				httpServletRequest.getSession().invalidate();
    			}
            }
            
        } catch (final Exception e) {
            httpServletRequest.getSession().invalidate();
        }

        final PrintWriter writer = httpServletResponse.getWriter();
        final RestResponse<String> responseObj = new RestResponse<>(null,
                new RestStatus<>("200", "User Successfully Logout"));
        final String result = mapper.writeValueAsString(responseObj);
        log.info(result);
        writer.write(result);
        writer.flush();
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}

}
