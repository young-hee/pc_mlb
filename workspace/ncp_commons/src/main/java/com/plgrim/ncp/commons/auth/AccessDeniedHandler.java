package com.plgrim.ncp.commons.auth;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
/* 로그인이 인증이후 인증 없는 페이지 접근할때 이벤트가 발생 한다 */
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler{

    String expiredURL;

	@Override
    public void handle(HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException,
            ServletException {

        log.error("",accessDeniedException);

        if(accessDeniedException instanceof MissingCsrfTokenException) {
            response.sendRedirect(expiredURL);
        } else {
        	if(log.isWarnEnabled()){
                log.warn("access deny {}!!!", request.getRequestURI());
        	}
            response.sendRedirect("/erros/accessDeny");
        }
    }

}
