package com.erp.service;

import java.nio.file.attribute.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.exception.PrincipalNotFoundException;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.erp.utils.GlobalConstants;

import lombok.extern.slf4j.Slf4j;

/*
 * Service class for sending email and other common methods which can be used accross all services
 * (including the activation link creation if needed)
 */
@Service
@Slf4j
public class BaseService {

	public <T> ResponseEntity<RestResponse<T>> createRestResponseEntity(final HttpStatus httpStatus,
			final T paramObject, final String msg, final String... msgParams) {
		final RestResponse<T> restResponse = new RestResponse<>(paramObject,
				new RestStatus<>(httpStatus.toString(), msg));
		return new ResponseEntity<>(restResponse, httpStatus);
	}

	public static HttpServletRequest getHttpServletRequest() {

		ServletRequestAttributes servletRequest = null;
		try {
			servletRequest = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		} catch (final Exception e) {
			log.debug("Unable to get Request Object", e);
		}
		if (servletRequest == null) {
			return null;
		}
		return servletRequest.getRequest();
	}

	public HttpServletResponse getHttpServletResponse() {
		final HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getResponse();
		return response;
	}

	public UserPrincipal getPrinciple() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return (UserPrincipal) authentication.getPrincipal();
		}
		throw new PrincipalNotFoundException(GlobalConstants.PRINCIAPL_NOT_FOUND_EXCEPTION_MESSAGE);
	}

}
