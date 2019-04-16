package com.erp.service;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.erp.auth.SessionTracker;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenAuthenticationService {
	private static final String UNSUPPORTED_JWT_TOKEN = "Unsupported Jwt token";
	private static final String MALFORMED_JWT_TOKEN = "Malformed Jwt token";
	private static final String JWT_TOKEN_EXPIRED = "Jwt token expired";
	private static final String JWT_SIGNATURE_MISMATCH = "Jwt Signature mismatch";
	private static final String ILLEGAL_ARGUEMENT_EXCEPTION = "Illegal Arguement Exception";
	private static long expirationTime = 30; // 30 min
	public static final String SECRET = "ThisIsASecret";
	public static final String BEARER_TOKEN_PREFIX = "Bearer";
	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private SessionTracker sessionTracker;

	public String addAuthentication(final HttpServletResponse res, final String username,
			final HttpServletRequest req) {
		final String sessionId = req.getSession().getId();
		log.debug("Session Id getting saved is:{} ", sessionId);
		log.debug("Adding SessionId to Jwt for SessionId = {}", sessionId);
		log.debug("res {}", res);
		final String JWT = Jwts.builder().setSubject(username).setId(sessionId)
				.setExpiration(new Date(System.currentTimeMillis() + (expirationTime * 60 * 1000)))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		sessionTracker.map.put(sessionId, req.getSession());
		return BEARER_TOKEN_PREFIX + " " + JWT;
	}

	public Authentication getAuthentication(final HttpServletRequest request) {
		final String token = request.getHeader(AUTHORIZATION_HEADER);
		if (token != null && token.contains(BEARER_TOKEN_PREFIX)) {
			// parse the token.
			Claims claims;
			try {
				claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(BEARER_TOKEN_PREFIX, "")).getBody();

			} catch (final ExpiredJwtException e) {
				log.info(JWT_TOKEN_EXPIRED, e);
				return null;
			} catch (final UnsupportedJwtException e) {
				log.info(UNSUPPORTED_JWT_TOKEN, e);
				return null;
			} catch (final MalformedJwtException e) {
				log.info(MALFORMED_JWT_TOKEN, e);
				return null;
			} catch (final SignatureException e) {
				log.info(JWT_SIGNATURE_MISMATCH, e);
				return null;
			} catch (final IllegalArgumentException e) {
				log.info(ILLEGAL_ARGUEMENT_EXCEPTION, e);
				return null;
			}
			final String user = claims.getSubject();
			final String sessionId = claims.getId();

			log.debug("Getting Authenticate for SessionId = {}", sessionId);
			sessionTracker.map.forEach((key, value) -> {
				log.debug("Key : {}, Value : {}", key, value);
			});
			final HttpSession session = sessionTracker.map.get(sessionId);

			log.debug("Getting Authenticate for the Session ={} ", session);
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}

		return null;
	}

	public String getSessionId(final HttpServletRequest request) {
		final String token = request.getHeader(AUTHORIZATION_HEADER);
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(BEARER_TOKEN_PREFIX, "")).getBody().getId();

	}

	public String checkAuthentication(final String token) {

		if (token != null && token.contains(BEARER_TOKEN_PREFIX)) {
			// parse the token.
			Claims cliams;

			try {
				cliams = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(BEARER_TOKEN_PREFIX, "")).getBody();
			} catch (final ExpiredJwtException e) {
				log.info(JWT_TOKEN_EXPIRED, e);
				return JWT_TOKEN_EXPIRED;
			} catch (final UnsupportedJwtException e) {
				log.info(UNSUPPORTED_JWT_TOKEN, e);
				return UNSUPPORTED_JWT_TOKEN;
			} catch (final MalformedJwtException e) {
				log.info(MALFORMED_JWT_TOKEN, e);
				return MALFORMED_JWT_TOKEN;
			} catch (final SignatureException e) {
				log.info(JWT_SIGNATURE_MISMATCH, e);
				return JWT_SIGNATURE_MISMATCH;
			} catch (final IllegalArgumentException e) {
				log.info(ILLEGAL_ARGUEMENT_EXCEPTION, e);
				return ILLEGAL_ARGUEMENT_EXCEPTION;
			}

			final String sessionId = cliams.getId();

			log.debug("Getting Authenticate for SessionId = {}", sessionId);
			final HttpSession session = sessionTracker.map.get(sessionId);

			log.debug("Getting Authenticate for the Session ={} ", session);
			if (session == null) {

				log.info(JWT_TOKEN_EXPIRED);
				return JWT_TOKEN_EXPIRED;
			}
			return "Good and working Jwt token";
		}
		return "Not valid jwt formed token";
	}

	public static long getExpirationTime() {
		return expirationTime;
	}

	public static void setExpirationTime(final long expirationTime1) {
		expirationTime = expirationTime1;
	}

}