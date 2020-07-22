package com.plgrim.ncp.framework.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.enums.SecurityParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 기본 AccessDeniedHandler. CSRF 토근 세션이 만료되면 세션 만료 페이지로 이동하고 그렇지 않은 경우 403 페이지로
 * 이동 한다.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SecurityAccessDeniedHandler extends AccessDeniedHandlerImpl {

	String expiredURL;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		if (handleRedirect(request, response, accessDeniedException)) {
			return; // XXX 예전에는 무조건 super를 호출했었음.. 만약 운영 배포후 이상 동작이 발생하면 여기부터
					// 체크할것
		}
		responseErrorPage(request, response, accessDeniedException);
	}

	boolean handleRedirect(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException {
		//@formatter:off
		return     handleNotAuthenticated(request, response, accessDeniedException) 
				|| handleExpired(response, accessDeniedException);
		//@formatter:on
	}

	void responseErrorPage(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		super.handle(request, response, accessDeniedException);
	}

	boolean handleNotAuthenticated(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
		try {
			return redirectNotAuthenticatedInternal(request, response, accessDeniedException);
		} catch (Exception e) {
			// 최종적으로는 super.handle에 의해 처리되기 때문에 여기에서는 오류가 발생해도 무시한다
			log.warn("delegate to super.handle()", e);
			return false;
		}
	}

	boolean redirectNotAuthenticatedInternal(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, UnsupportedEncodingException {
		if (isAuthenticated()) {
			return false;
		}
		log.info("Anonymous user access this url: {} thrownedException: {}", request.getRequestURL(), accessDeniedException);
		response.sendRedirect(urlToLogin(request));
		return true;
	}

	boolean isAuthenticated() {
		return isUser() || isGuest() == false;
	}

	boolean isUser() {
		return ContextService.hasRole();
	}

	boolean isGuest() {
		if (hasNoAuthority()) {
			return false;
		}
		return hasGuestRole();
	}

	/**
	 * ROLE_GUEST는 비회원 인증 절차를 거친 사용자만 가질수 있음
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	boolean hasGuestRole() {
		for (GrantedAuthority authority : (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities()) {
			if (SecurityParam.GUEST_ROLE.toString().equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	boolean handleExpired(HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
		if (accessDeniedException instanceof MissingCsrfTokenException) {
			response.sendRedirect(expiredURL);
			return true;
		}
		return false;
	}

	String urlToLogin(HttpServletRequest request) throws UnsupportedEncodingException {
		return "/member/login/view?loginTarget=" + URLEncoder.encode(request.getRequestURI(), "UTF-8");
	}

	boolean hasNoAuthority() {
		return SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null
				|| SecurityContextHolder.getContext().getAuthentication().getAuthorities() == null
				|| SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() == 0;
	}

}
