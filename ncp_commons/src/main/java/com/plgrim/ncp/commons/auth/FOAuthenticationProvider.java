package com.plgrim.ncp.commons.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FOAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		// ERP 비밀번호 확인
		boolean flag = false;
		flag = getPasswordEncoder().isPasswordValid(userDetails.getPassword(),
				presentedPassword, null);
//		if (!flag) {
//			logger.debug("Authentication failed: password does not match stored value");
//
//			throw new BadCredentialsException(messages.getMessage(
//					"AbstractUserDetailsAuthenticationProvider.badCredentials",
//					"Bad credentials"));
//		}
		
		// 비밀번호 확인
		if (!flag && !getPasswordEncoder() .isPasswordValid(userDetails.getPassword(),
				presentedPassword, null)) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}

	@Override
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
}

