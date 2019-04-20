package com.erp.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class PrincipalNotFoundException extends AuthenticationCredentialsNotFoundException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6425303443245628252L;

	/**
	 * @param msg
	 */
	public PrincipalNotFoundException(String msg) {
		super(msg);
	}

	public PrincipalNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
