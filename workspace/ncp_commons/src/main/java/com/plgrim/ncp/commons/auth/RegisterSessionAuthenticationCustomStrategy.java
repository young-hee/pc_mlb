package com.plgrim.ncp.commons.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

public class RegisterSessionAuthenticationCustomStrategy implements
        SessionAuthenticationStrategy {

	@Override
	public void onAuthentication(Authentication authentication,
	        HttpServletRequest request, HttpServletResponse response)
	        throws SessionAuthenticationException {
		// TODO Auto-generated method stub

	}
}
