package com.plgrim.ncp.commons.auth.naver;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class NaverAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String NAVER_VALIDATION_RESULT = "NaverValidationResult";

	private final static String DEFAULT_TOKEN_KEY_NAME = "accessToken";

	String accessTokenParameterName = DEFAULT_TOKEN_KEY_NAME;

	private boolean postOnly = true;

	@Autowired
	NaverAccessTokenValidator validator;

	/**
	 * Interface Server 에서는
	 * NaverUserService에 대해 구현체가 없어 DI 불가
	 */
	@Autowired(required = false)
	NaverUserService naverUserService;
	
	@Autowired
    IdGenService idGenService;

	protected NaverAuthenticationFilter() {
		super(new AntPathRequestMatcher("/naverloginProcess", "POST"));
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info(CommonResponseCode.MB_00110_SNS_네이버_로그인_시도.toMessage());

		log.info("Naver authentication is requested");
		if (postOnly && !request.getMethod().equals("POST")) {
			log.info(CommonResponseCode.MB_40045_SNS_네이버_로그인_실패.toMessage());

			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String accessToken = obtainNaverAccessToken(request);
		log.info("Requested accessToken: {}", accessToken);
		if (StringUtils.isEmpty(accessToken)) {
			log.info(CommonResponseCode.MB_40045_SNS_네이버_로그인_실패.toMessage());

			log.debug("accessToke is null.");
			return null;
		}
		
		try {
			SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request);
			NaverValidationResult validationResult = validator.validate(systemPK, accessToken);
			request.getSession(false).setAttribute(NAVER_VALIDATION_RESULT,validationResult);
			String NaverUserId = validationResult.getUserId();
			String NaverEmail = validationResult.getEmail();
			log.info("NaverUserId: {} NaverEmail: {} ",NaverUserId,  NaverEmail);
			
			String memberIdCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER.toString();
			if(systemPK.getMall().equals("MBM")){
				memberIdCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_MBM.toString();
			}else if(systemPK.getMall().equals("SAM")){
				memberIdCntcTpCd = MemberEnum.MemberIdCntcTpCd.NAVER_SAM.toString();
			}
			
			String plgrimshopLoginId = naverUserService.findPlgrimshopLoginIdByNaverUserId(NaverUserId, null, false, memberIdCntcTpCd);
			if (plgrimshopLoginId == null) {
				log.info("Naver User '{}' not found ", NaverUserId);
				throw new UsernameNotFoundException("Bad credentials");
			}
			log.info("Retrived plgrimshop login id: {}", plgrimshopLoginId);
			NaverAuthenticationToken authRequest = new NaverAuthenticationToken(plgrimshopLoginId);
			
			HttpSession session = request.getSession();
			//네이버 로그인 사용자임 세션값 셋팅.
	        session.setAttribute(SessionEnum.ID_CNTC_TP_CD.toString(), "NAVER");
	        //네이버 로그인 email
	        session.setAttribute("NAVER_EMAIL", NaverEmail);
	        log.debug("Authentication success. Updating SecurityContextHolder to contain session.getAttribute(SessionEnum.ID_CNTC_TP_CD.toString()) : {}", session.getAttribute(SessionEnum.ID_CNTC_TP_CD.toString()));
			log.info(CommonResponseCode.MB_00111_SNS_네이버_로그인_성공.toMessage());

			return this.getAuthenticationManager().authenticate(authRequest);
		} catch (NaverLoginException validationFailed) {
			log.info(CommonResponseCode.MB_40045_SNS_네이버_로그인_실패.toMessage());

			log.error("Naver login failed", validationFailed);
			throw new BadCredentialsException("Bad credentials");
		} catch (Exception repositoryProblem) {
			if (repositoryProblem.getClass().getName().contains("UsernameNotFoundException")) {
				log.info(CommonResponseCode.MB_00112_SNS_네이버_로그인_연동_정보_없음.toMessage());
			}
			else if (repositoryProblem.getClass().getName().contains("IllegalArgumentException")) {
				log.info(CommonResponseCode.MB_40045_SNS_네이버_로그인_실패.toMessage());
				
				throw new IllegalArgumentException("Bad credentials");
			}
			else {
				log.info(CommonResponseCode.MB_40045_SNS_네이버_로그인_실패.toMessage());
			}

			log.error("Naver login repository failed", repositoryProblem);
			throw new InternalAuthenticationServiceException("Bad credentials");
		}
	}

	protected String obtainNaverAccessToken(HttpServletRequest request) {
		return request.getParameter(accessTokenParameterName);
	}

	/**
	 * 원래 spring 코드에서 rememberme 호출하는 부분 제거
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		log.info("Authentication success. Updating SecurityContextHolder to contain: {}", authResult);

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

		log.info("Authentication request failed: {}", failed.toString());
		log.info("Updated SecurityContextHolder to contain null Authentication");
		log.info("Delegating to authentication failure handler {}", getFailureHandler());

		getFailureHandler().onAuthenticationFailure(request, response, failed);
	}

}

