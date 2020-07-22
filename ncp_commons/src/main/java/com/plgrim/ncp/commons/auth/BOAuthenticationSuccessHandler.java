package com.plgrim.ncp.commons.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증이 성공이 된 후처리
 */
@Data
@Slf4j
public class BOAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/* redirect url */
	String defaultUrl;
	
	@Autowired
    AuthenticationComponent authenticationComponent;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			BOSecurityUserDetail userDetail = (BOSecurityUserDetail)authentication.getPrincipal();

			if(userDetail != null) {
				//로그인 히스트리를 업데이트한다.
	            log.debug(">>> session.getId() : {}", session.getId());
	            //BO,PO 사이트 사용자만 중복 로그인 방지를 위한 sessionId 저장
	            if(BOSecurityUtil.isBoSite() || BOSecurityUtil.isPoSite()) {
	            	authenticationComponent.updateLogin(userDetail.getSysAdmin().getAdminId(), session.getId());
	            } else {
	            	authenticationComponent.updateLogin(userDetail.getSysAdmin().getAdminId(), "");
	            }
			}
			
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		} catch (Exception ex) {
			log.error("", ex);
			throw new AuthenticationServiceException("Credentials could not be verified", ex);
		}
	}
}
