package com.plgrim.ncp.commons.auth.naver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaverAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return (NaverAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// DaoAuthenticationProvider 에서는 패스워드 체크를 하지만, 여기에서는 하지 않는다. 
	}
	
	@Override
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

}

