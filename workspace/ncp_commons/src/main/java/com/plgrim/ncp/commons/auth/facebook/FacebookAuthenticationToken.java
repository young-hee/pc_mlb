package com.plgrim.ncp.commons.auth.facebook;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FacebookAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 4784591897074559033L;

	public FacebookAuthenticationToken(Object principal) {
		super(principal, null);
		this.accessToken = (String) principal;
		setAuthenticated(false);
	}

	String accessToken;

	@Override
	public Object getCredentials() {
		return accessToken;
	}

	@Override
	public Object getPrincipal() {
		return accessToken;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		accessToken = null;
	}
}
