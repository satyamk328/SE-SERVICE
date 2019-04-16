package com.erp.auth;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.erp.audit.dao.CentralizedLogDao;
import com.erp.exception.UnAuthorizedException;
import com.erp.exception.UnReachableLdapServerException;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", "application/json");

        String errorMessage = "Unknown username or bad password";

        if (exception instanceof UnAuthorizedException) {
            errorMessage = "User is not authorised to use the Admin Application";
        } else if (exception instanceof UnReachableLdapServerException) {
        }

        PrintWriter writer = response.getWriter();
        RestResponse<String> responseObj = new RestResponse<>(null,
                new RestStatus<>("UNAUTHORIZED", errorMessage));
        
        String result = mapper.writeValueAsString(responseObj);
        log.debug("Login Response : {}", result);
        writer.write(result);
        writer.flush();
    }
}