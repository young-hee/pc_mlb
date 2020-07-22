package com.plgrim.ncp.cmp.member.fo.auth;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.RedirectOrder;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.security.SecuritySessionInitHandler;
import com.plgrim.ncp.framework.utils.XSSUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Remember Me 인증이 성공이 된 후 처리를 담당 한다.
 */
@Data
@Slf4j
public class SecurityAuthenticationRememberMeHandler implements AuthenticationSuccessHandler {

	RequestCache requestCache = new HttpSessionRequestCache();

	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/* referer 헤더 사용 유무 */
	boolean referer = false;
	
	/* 기본 성공 url */
	String defaultUrl;
	
	String popupSuccessURL;
	
	@Autowired(required = false)
	SecuritySessionInitHandler securitySessionInitHandler;
	
	@Autowired(required = false)
	SessionRegistry sessionRegistry;
	
	@Autowired
	IdGenService idGenService;
	
	@Autowired
	MemberBaseCommandService memberBaseCommandService;
	
	/**
	 * 프런트 (PC, MOBILE) 회원들에게 부여되는 권한.
	 */
	final static String FRONT_ROLE_NAME = "ROLE_USER";
	
	
	/** {@inheritDoc} */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			// ip
			String ip = request.getRemoteAddr();
			Locale locale = LocaleService.getCurrentLocale(request);
			MbrLoginLog mbrLoginLog = new MbrLoginLog();
			mbrLoginLog.setMbrNo(userDetail.getMbr().getMbrNo());
			mbrLoginLog.setMbrLoginCd("LOGIN");
			mbrLoginLog.setLoginIp(ip);

			String loginNationCd = locale.getCountry();

			if (StringService.isNotEmpty(loginNationCd)) {
				mbrLoginLog.setLoginNationCd(loginNationCd.toLowerCase());
			} else {
				mbrLoginLog.setLoginNationCd("kr");
			}

			mbrLoginLog.setLangCd(pk.getLang());
			mbrLoginLog.setDvcCd(pk.getDevice());
			mbrLoginLog.setMallId(pk.getMall());
			// 광고 제휴로 들어 왔을시
			if (session.getAttribute(SessionEnum.INFLOW_SN.toString()) != null) {
				long inflowSn = Long.parseLong((String) session.getAttribute(SessionEnum.INFLOW_SN.toString()));
				mbrLoginLog.setInflowSn(inflowSn);

			}
			log.debug("locale {} ", locale);

			// 로그인 히스토리 추가
			log.info(">>> RememberMe mbrLoginLog {}", mbrLoginLog);
			memberBaseCommandService.addMbrLoginLog(pk, mbrLoginLog);

			// 비밀번호는 세션에 담을수 없으므로
			userDetail.getMbr().setMbrPw("");
						
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority(FRONT_ROLE_NAME));
			authentication = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);

			// 세션 정보를 체크하여 중복로그인을 제거한다.
			// 마지막 세션을 threadlocal에 저장
			if (securitySessionInitHandler != null && sessionRegistry != null) {
				securitySessionInitHandler.initSecuritySession(request, response, authentication, sessionRegistry);
			}
			
			RedirectOrder redirectOrder = decideRedirectStrategy(request, response);

			switch (redirectOrder) {
			case TARGET_PARAM:
				useTargetUrl(request, response);
				break;
			case SESSION_URL:
				useSessionUrl(request, response);
				break;
			case SESSION_REFERER_URL:
				useSessionRefererUrl(request, response);
				break;
			case HEADER_REFERER:
				useRefererUrl(request, response);
				break;
			default:
				useDefaultUrl(request, response);
			}
			
			/*
			String reqUrl = request.getRequestURL().toString();
			String queryString = StringUtils.defaultString(request.getQueryString());
			if (!StringUtils.isEmpty(queryString)) {
				reqUrl = reqUrl + "?" + queryString;
	        }
			log.info(">>> SecurityAuthenticationRememberMeHandler Redirect : [" + reqUrl + "] ");
			redirectStrategy.sendRedirect(request, response, reqUrl);
			*/
		} catch (Exception e) {
			log.warn("SecurityAuthenticationRememberMeHandler Error : {}", e);
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		}
	}
	
	/**
	 * 인증 성공후 어떤 URL로 redirect 할지를 결정한다
	 * <p/>
	 * 순서되로 체크를 하며 먼저 해당이 되는 것을 처리 하도록 한다.
	 * <p/>
	 * <ol>
	 * <li> 1순위 헤더 값 ajax-login이 true일 경우
	 * <li> 2순위 loginTarget 파라미터 값을 읽은 URL이 존재할 경우
	 * <li> 3순위 Spring Security가 세션에 저장한 URL이 존재할 경우
	 * <li> 4순위 Request의 REFERER의 URL이 존재 할 경우
	 * <li> 5순위 모두 해당 사항이 없으면 Default URL를 사용 한다.
	 *</ol>
	 * @param request
	 * @param response
	 * @return RedirectOrder
	 */
	private RedirectOrder decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RedirectOrder redirectOrder = RedirectOrder.DEFAULT;
		HttpSession session = request.getSession();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		// request 부터 호출한 URL 정보가 있는지 파악을 한다.
		String targetURL = request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString());
		// targetURL이 존재 하면
		if (!isEmpty(targetURL)) {
			return RedirectOrder.TARGET_PARAM;
		} else {

			// 세션에 URL 정보가 있을 경우
			if (savedRequest != null) {
				return RedirectOrder.SESSION_URL;
			} else {
				if (session != null && session.getAttribute("REFERER_URL") != null) {
					return RedirectOrder.SESSION_REFERER_URL;

				} else {
					// referer 헤더를 사용할 경우
					if (referer) {
						String refererUrl = request.getHeader("REFERER");
						if (isEmpty(refererUrl)) {
							return RedirectOrder.HEADER_REFERER;
						}
					}

				}
			}
		}
		return redirectOrder;

	}
	
	private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			requestCache.removeRequest(request, response);
		}
		String targetUrl = XSSUtil.stripXSS(request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString()));

		// 로그인 모드가 팝업일 경우 팝업 로그인 성공 페이지로 이동 한다.
		String pageMode = request.getParameter(SecurityParam.LOGIN_POPUP_ENALBED.toString());

		// pageMode값 true일 경우
		if (StringService.isNotEmpty(pageMode) && BooleanUtils.toBoolean(pageMode)) {
			targetUrl = popupSuccessURL + "?" + SecurityParam.TARGET_PARAM_NAME.toString() + "=" + targetUrl;
		}

		renderView(request, response, targetUrl);

	}

	private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		renderView(request, response, targetUrl);
	}

	private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String targetUrl = request.getHeader("REFERER");
		renderView(request, response, targetUrl);
	}

	private void useSessionRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String targetUrl = (String) session.getAttribute("REFERER_URL");
		renderView(request, response, targetUrl);
	}

	private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		renderView(request, response, defaultUrl);
	}

	private void renderView(HttpServletRequest request, HttpServletResponse response, String targetURL) throws IOException {

		boolean ajaxAvailable = BooleanUtils.toBoolean(request.getHeader(SecurityParam.HEADER_AJAX_LOGIN.toString()));

		/************************************************************************
		 * (ID - 확인필요)
		 * URL 확인용 로그 시작
		 ************************************************************************/
		String reqUrl = request.getRequestURL().toString();
		String queryString = StringUtils.defaultString(request.getQueryString());
		if (!StringUtils.isEmpty(queryString)) {
			reqUrl = reqUrl + "?" + queryString;
        }
		log.info(">>> SecurityAuthenticationSuccessHandler reqUrl : [" + reqUrl + "] ");
		/************************************************************************
		 * (ID - 확인필요)
		 * URL 확인용 로그 종료
		 ************************************************************************/
		
		// 요청이 ajax 면
		if (ajaxAvailable) {
			String releaseDrmncy = "";
			if( request.getSession().getAttribute(SecurityParam.RELEASE_DRMNCY.toString()) != null ) {
				releaseDrmncy = (String) request.getSession().getAttribute(SecurityParam.RELEASE_DRMNCY.toString());
				request.getSession().removeAttribute(SecurityParam.RELEASE_DRMNCY.toString());
			}

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			SecurityJsonResult resultData = new SecurityJsonResult();
			resultData.setLoginTarget(targetURL);
			resultData.setReleaseDrmncy(releaseDrmncy);
			String jsonData = defaultUrl;
			try {
				jsonData = JsonService.marshalling(resultData);
			} catch (Exception e) {
				throw new IOException(e);
			}
			InputStream is = new ByteArrayInputStream(jsonData.getBytes());
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		}
		else {
			redirectStrategy.sendRedirect(request, response, targetURL);
		}
	}
	
}
