package com.erp.exception;

import org.springframework.security.core.AuthenticationException;

public class UnReachableLdapServerException  extends AuthenticationException {
    // ~ Constructors
    // ===================================================================================================

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public UnReachableLdapServerException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public UnReachableLdapServerException(String msg, Throwable t) {
        super(msg, t);
    }
}

