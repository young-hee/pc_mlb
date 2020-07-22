package com.plgrim.ncp.commons.auth.facebook;

import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
public class FacebookAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final static String DEFAULT_TOKEN_KEY_NAME = "accessToken";

	String accessTokenParameterName = DEFAULT_TOKEN_KEY_NAME;

	private boolean postOnly = true;

	@Autowired
	FacebookAccessTokenValidator validator;

	@Autowired
	FacebookUserService facebookUserService;;

	protected FacebookAuthenticationFilter() {
		super(new AntPathRequestMatcher("/fbloginProcess", "POST"));
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info(CommonResponseCode.MB_00105_SNS_페이스북_로그인_시도.toMessage());

		log.info("Facebook authentication is requested");
		if (postOnly && !request.getMethod().equals("POST")) {
			log.info(CommonResponseCode.MB_40043_SNS_페이스북_로그인_실패.toMessage());

			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String accessToken = obtainFacebookAccessToken(request);
		log.info("Requested accessToken: {}", accessToken);
		if (StringUtils.isEmpty(accessToken)) {
			log.info(CommonResponseCode.MB_40043_SNS_페이스북_로그인_실패.toMessage());

			log.debug("accessToke is null.");
			return null;
		}

		try {
			ValidationResult validationResult = validator.validate(accessToken);
			String facebookUserId = validationResult.getUserId();
			String plgrimshopLoginId = facebookUserService.findPlgrimshopLoginIdByFacebookUserId(facebookUserId, null, false);
			if (plgrimshopLoginId == null) {
				log.info("Facebook User '{}' not found ", facebookUserId);
				throw new UsernameNotFoundException("Bad credentials");
			}
			log.info("Retrived plgrimshop login id: {}", plgrimshopLoginId);
			FacebookAuthenticationToken authRequest = new FacebookAuthenticationToken(plgrimshopLoginId);

			log.info(CommonResponseCode.MB_00106_SNS_페이스북_로그인_성공.toMessage());

			return this.getAuthenticationManager().authenticate(authRequest);
		} catch (FacebookLoginException validationFailed) {
			log.info(CommonResponseCode.MB_40043_SNS_페이스북_로그인_실패.toMessage());

			log.warn("Facebook login failed", validationFailed);
			throw new BadCredentialsException("Bad credentials");
		} catch (Exception repositoryProblem) {
			if (repositoryProblem.getClass().getName().contains("UsernameNotFoundException")) {
				log.info(CommonResponseCode.MB_00107_SNS_페이스북_로그인_연동_정보_없음.toMessage());
			} else {
				log.info(CommonResponseCode.MB_40043_SNS_페이스북_로그인_실패.toMessage());
			}


			log.warn("Facebook login repository failed", repositoryProblem);
			throw new InternalAuthenticationServiceException("Bad credentials");
		}
	}

	protected String obtainFacebookAccessToken(HttpServletRequest request) {
		return request.getParameter(accessTokenParameterName);
	}

	/**
	 * 원래 spring 코드에서 rememberme 호출하는 부분 제거
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		log.debug("Authentication success. Updating SecurityContextHolder to contain: {}", authResult);

		SecurityContextHolder.getContext().setAuthentication(authResult);

		// Fire event
		if (this.eventPublisher != null) {
			eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}

		getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();

		log.debug("Authentication request failed: {}", failed.toString());
		log.debug("Updated SecurityContextHolder to contain null Authentication");
		log.debug("Delegating to authentication failure handler {}", getFailureHandler());

		getFailureHandler().onAuthenticationFailure(request, response, failed);
	}

}
